package com.example.parkfinder

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.view.InflateException
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_park_item.*
import java.io.*
import java.lang.reflect.Type
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class parkItem(val data:Park, val main:Boolean) : AppCompatDialogFragment() {

    companion object{

        lateinit var myView:View
    }
    lateinit var googlemap:GoogleMap

    //var loc = LatLng(37.554752, 126.970631)
    lateinit var loc:LatLng

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        parkName.text = data.parkName
        parkInfo.text = data.toString()
        if((data.lat != null) && (data.lng != null)){
            loc = LatLng(data.lat.toDouble(), data.lng.toDouble())
        }

        parkItemCloseBtn.setOnClickListener {
            this.dialog?.dismiss()
        }
        if(main){
            addBookmarkBtn.text = "즐겨찾기 추가"
            addBookmarkBtn.setOnClickListener {
                //북마크에 해당 정보 있는지 검사하고 등록하기
                var array = ArrayList<Park>()

                val gson = Gson()
                val file = File(context!!.filesDir, "bookmarkData.json")


                if(!file.exists()){
                    val out = context!!.openFileOutput("bookmarkData.json", Context.MODE_APPEND)
                    out.write("[]".toByteArray())
                    out.close()
                }

                try{

                    var fin: InputStream = file.inputStream()
                    val reader = fin.bufferedReader()

                    val myType: Type = object: TypeToken<ArrayList<Park>>(){}.type as Type
                    array = gson.fromJson(reader, myType)
                    System.out.println(array.size)
                    var isBookmarked = false
                    for(item in array){
                        if (data.manageNum.equals(item.manageNum)){
                            Toast.makeText(context, "이미 즐겨찾기에 추가된 항목입니다.", Toast.LENGTH_SHORT).show()
                            isBookmarked = true
                            break
                        }
                    }
                    if(!isBookmarked){
                        array.add(data)
                        var fout: OutputStream = file.outputStream()
                        var wr:OutputStreamWriter = OutputStreamWriter(fout)
                        wr.write(gson.toJson(array, myType))
                        wr.close()

                        Toast.makeText(context, "즐겨찾기 추가", Toast.LENGTH_SHORT).show()
                    }

                }catch(e:Exception){
                    e.printStackTrace()
                }

            }
        }else{
            addBookmarkBtn.text = "즐겨찾기 삭제"
            var array = ArrayList<Park>()
            addBookmarkBtn.setOnClickListener {
                val gson = Gson()
                val file = File(context!!.filesDir, "bookmarkData.json")


                if(!file.exists()){
                    val out = context!!.openFileOutput("bookmarkData.json", Context.MODE_APPEND)
                    out.write("[]".toByteArray())
                    out.close()
                }

                try{

                    var fin: InputStream = file.inputStream()
                    val reader = fin.bufferedReader()

                    val myType: Type = object: TypeToken<ArrayList<Park>>(){}.type as Type
                    array = gson.fromJson(reader, myType)


                    for(item in array){
                        if (data.manageNum.equals(item.manageNum)){
                            Toast.makeText(context, "삭제 완료.", Toast.LENGTH_SHORT).show()
                            array.remove(data)
                            var fout: OutputStream = file.outputStream()
                            var wr: OutputStreamWriter = OutputStreamWriter(fout)
                            wr.write(gson.toJson(array, myType))
                            wr.close()
                            break
                        }
                    }


                }catch(e:Exception){
                    e.printStackTrace()
                }
            }



        }


        //initmap
        val mapFragment = fragmentManager?.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync{
            googlemap = it

            googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.toFloat()))

            val options = MarkerOptions()
            options.position(loc)
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            options.title(data.parkName)
            val mk1 = googlemap.addMarker(options)
            mk1.showInfoWindow()

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        //map.onDestroy()

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        try{
            myView = activity!!.layoutInflater.inflate(
                R.layout.fragment_park_item,
                LinearLayout(activity),
                false
            )
        }catch(e:InflateException){
            //맵이 존재함
            return AlertDialog.Builder(activity!!)
                .setView(myView)
                .create()
        }


        // dialog 빌드
        return AlertDialog.Builder(activity!!)
            .setView(myView)
            .create()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_park_item, container, false)

        if(myView != null){
            try{
                var parent:ViewGroup = myView.parent as ViewGroup
                parent?.removeView(myView)
            }catch (e:UninitializedPropertyAccessException){

            }catch (e:TypeCastException){

            }
        }

        return myView
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    /*override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()
    }*/


}
