package com.example.parkfinder

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.fragment_drawer_info.*

/**
 * A simple [Fragment] subclass.
 */
class drawerInfo(val data:String) : AppCompatDialogFragment() {

    lateinit var myView:View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        myView = activity!!.layoutInflater.inflate(R.layout.fragment_drawer_info, LinearLayout(activity), false)



        return AlertDialog.Builder(activity!!)
            .setView(myView)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            .create()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        drawerInfoContent.text = data
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_drawer_info, container, false)

        return myView
    }

}
