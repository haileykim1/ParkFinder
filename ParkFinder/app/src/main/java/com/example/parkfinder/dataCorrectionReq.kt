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
import kotlinx.android.synthetic.main.fragment_data_correction_req.*
import kotlinx.android.synthetic.main.fragment_dev_feedback.*

/**
 * A simple [Fragment] subclass.
 */
class dataCorrectionReq : AppCompatDialogFragment() {

    lateinit var myView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return myView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        myView = activity!!.layoutInflater.inflate( R.layout.fragment_data_correction_req, LinearLayout(activity), false)

        return AlertDialog.Builder(activity!!)
            .setView(myView)
            .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            .setPositiveButton("SUBMIT", DialogInterface.OnClickListener { dialog, which ->
                val msg:String = reqParkEdit.text.toString()
                val msg2:String = reqContentEdit.text.toString()
                val totalMsg = msg + msg2
                if(totalMsg.trim().length > 0){
                    sendEmail("공원명 : " + msg + " /n정정내용 : " + msg2)
                }
            })
            .create()
    }

    private fun sendEmail(msg:String){
        Log.i("Send email", "correction request")

        val devAddress = Array<String>(1){"soyangkim98@gmail.com"}
        val subject:String = "ParkFinder 데이터 정정 요청"
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
