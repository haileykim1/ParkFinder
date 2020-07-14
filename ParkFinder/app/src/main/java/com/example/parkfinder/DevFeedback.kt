package com.example.parkfinder

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.text.trimmedLength
import kotlinx.android.synthetic.main.fragment_dev_feedback.*

/**
 * A simple [Fragment] subclass.
 */
class DevFeedback : AppCompatDialogFragment() {

    lateinit var myView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return myView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        myView = activity!!.layoutInflater.inflate(R.layout.fragment_dev_feedback, LinearLayout(activity), false)

        return AlertDialog.Builder(activity!!)
            .setView(myView)
            .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            .setPositiveButton("SUBMIT", DialogInterface.OnClickListener { dialog, which ->
                val msg:String = devFeedbackEdit.text.toString()
                if(msg.trim().length > 0){
                    sendEmail(msg)
                }
            })
            .create()
    }

    private fun sendEmail(msg:String){
        Log.i("Send email", "to developer")

        val devAddress = Array<String>(1){"soyangkim98@gmail.com"}
        //val CC:String = subjectEdit.text.toString().trim()
        val subject:String = "ParkFinder 피드백"
        //launch email client
        val mIntent = Intent(Intent.ACTION_SEND)
        //val mIntent = Intent(Intent.ACTION_SEND, Uri.fromParts("mailto", recipient, null))

        mIntent.setData(Uri.parse("mailto:"))
        mIntent.setType("text/plain")


        mIntent.putExtra(Intent.EXTRA_EMAIL, devAddress)
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, msg)

        try{
            //email intent 시작
            startActivity(Intent.createChooser(mIntent, "Choose EmailClient"))

            //Toast.makeText(context, "피드백 전송 완료", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }



    }

}
