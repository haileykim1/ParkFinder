package com.example.parkfinder

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_theme_setting.*

/**
 * A simple [Fragment] subclass.
 */
class ThemeSetting : AppCompatDialogFragment() {

    lateinit var myView:View

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        themeSettingCloseBtn.setOnClickListener {
            this.dialog?.cancel()
        }

        darkVioletBtn.setOnClickListener {


            (context as MainActivity).setThemeDarkViolet()
        }

        coralBtn.setOnClickListener {
            (context as MainActivity).setThemeCoral()
        }

        turquoiseBtn.setOnClickListener {

            (context as MainActivity).setThemeTurquoise()
        }

        lightSkyBlueBtn.setOnClickListener {

            (context as MainActivity).setThemeLightSkyBlue()
        }


    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        var colorDrawable = ColorDrawable(ContextCompat.getColor(context!!, R.color.CCoral))
        activity.actionBar?.setBackgroundDrawable(colorDrawable)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        myView = activity!!.layoutInflater.inflate(
            R.layout.fragment_theme_setting,
            LinearLayout(activity),
            false
        )

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
        //return inflater.inflate(R.layout.fragment_theme_setting, container, false)



        return myView
    }

}
