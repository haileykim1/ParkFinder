package com.example.parkfinder

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.fragment_query_history.*
import java.io.File
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class QueryHistory : AppCompatDialogFragment() {

    lateinit var myView:View

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val file = File(context!!.filesDir, "queryData.txt")
        if(!file.exists()){
            val out = context!!.openFileOutput("queryData.txt", Context.MODE_APPEND)
            out.close()
        }
        val scan = Scanner(context?.openFileInput("queryData.txt"))
        val sb = StringBuilder()
        while(scan.hasNextLine()){
            sb.append(scan.nextLine() + "\n")
        }
        scan.close()
        queryHistoryView.text = sb.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return myView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        myView = activity!!.layoutInflater.inflate(R.layout.fragment_query_history, LinearLayout(activity), false)



        return AlertDialog.Builder(activity!!)
            .setView(myView)
            .setNegativeButton("삭제", DialogInterface.OnClickListener { dialog, which ->
                deleteHistory()
            })
            .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            .create()
    }
    fun deleteHistory(){
        val file = File(context!!.filesDir, "queryData.txt")
        if(file.exists())
            file.delete()
    }

}
