package com.example.parkfinder

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_bookmark.*
import kotlinx.android.synthetic.main.fragment_search.*
import java.io.File
import java.io.InputStream

/**
 * A simple [Fragment] subclass.
 */
class BookmarkFragment : Fragment() {

    var array = ArrayList<Park>()
    lateinit var adapter:searchViewAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        putData()
        bookmarkRcyView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = searchViewAdapter(array)
        adapter.itemClickListener = object:searchViewAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: searchViewAdapter.searchViewHolder,
                view: View,
                data: Park,
                position: Int
            ) {
                //Toast.makeText(context, "공원 상세 정보 출력", Toast.LENGTH_SHORT).show()
                parkItem(data, false).show(fragmentManager!!, "parkItem $position")
                putData()
                adapter = searchViewAdapter(array)
                bookmarkRcyView.adapter = adapter

                adapter.notifyDataSetChanged()
            }


        }
        bookmarkRcyView?.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    fun putData(){
        try{

            val gson = Gson()
            array.clear()
            val file = File(context!!.filesDir, "bookmarkData.json")
            if(!file.exists()){
                val out = context!!.openFileOutput("bookmarkData.json", Context.MODE_APPEND)
                out.close()
            }

            var fin: InputStream = file.inputStream()
            val reader = fin.bufferedReader()

            val myType = object: TypeToken<java.util.ArrayList<Park>>(){}.type
            array = gson.fromJson(reader, myType)


        }catch(e:Exception){
            e.printStackTrace()
        }
    }

}
