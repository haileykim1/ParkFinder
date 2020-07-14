package com.example.parkfinder

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_search.*
import java.io.*
import java.lang.ref.WeakReference
import java.net.URL
import java.nio.charset.Charset
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    val html = "http://api.data.go.kr/openapi/tn_pubr_public_cty_park_info_api"
    val key = "R22h%2B3kSIk%2BWWG1q1INmmLoKE%2Fw8DkQJDVQviuOznnUqUe1Jk50jmBo8pp4cDe6aPyG40Sxj7VmoOaH4N30X5w%3D%3D"
    val urlBuilder :StringBuilder = StringBuilder(html)
    var array = ArrayList<Park>()
    var array2 = ArrayList<Park>()
    var array3 = ArrayList<Park>()
    lateinit var adapter:searchViewAdapter
    var gson: Gson = Gson()
    var cityStr = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        searchRcyView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        resultButton.setOnClickListener {
            if(array.size == 0){
                getParkObj()
                //공원정보 18991개
            }

            if(((edit1.text.toString().trim().length)==0)&&(cityStr.trim().length == 0)) {
                array3 = array

            }
            else{
                array2.clear()
                searchQuery()
                array3 = array2
            }


            adapter = searchViewAdapter(array3)
            adapter.itemClickListener = object:searchViewAdapter.OnItemClickListener{
                override fun OnItemClick(
                    holder: searchViewAdapter.searchViewHolder,
                    view: View,
                    data: Park,
                    position: Int
                ) {
                    //Toast.makeText(context, "공원 상세 정보 출력", Toast.LENGTH_SHORT).show()
                    parkItem(data, true).show(fragmentManager!!, "parkItem $position")
                }


            }
            searchRcyView?.adapter = adapter


        }



        //init spinner
        val adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item, ArrayList<String>())
        val cityNames = resources.getStringArray(R.array.city).toList()
        adapter.addAll(cityNames)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                cityStr = ""
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{
                        cityStr=""
                    }
                    else->{
                        cityStr=cityNames[position]
                    }
                }
            }

        }



    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_search, container, false)
    }




    fun getParkObj(){
        //ArrayList
        var am = resources.assets
        try{

            var fin:InputStream = am.open("parkdata.json")
            val reader = fin.bufferedReader()

            val myType = object: TypeToken<ArrayList<Park>>(){}.type
            array = gson.fromJson(reader, myType)


        }catch(e:Exception){
            e.printStackTrace()
        }
    }



    fun searchQuery() {

        System.out.println(array.size)
        val queryName:String = edit1.text.toString()
        saveQuery(queryName)
        for (item in array){
            if(item.parkName?.contains(queryName)!!){
                if(cityStr.equals("")) {

                    array2.add(item)

                }else{
                    if(item.numAdr == null){
                        if(item.rdnameAdr?.contains(cityStr)!!){
                            array2.add(item)
                        }
                    }else{
                        if(item.numAdr?.contains(cityStr)!!){
                            array2.add(item)
                        }
                    }
                }
            }
        }
        if(array2.size == 0){
            Toast.makeText(context, "검색 결과 없음", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveQuery(str:String){


        val outwrite = PrintStream(context?.openFileOutput("queryData.txt", Context.MODE_APPEND))
        outwrite.println(str)
        outwrite.close()



    }

}
