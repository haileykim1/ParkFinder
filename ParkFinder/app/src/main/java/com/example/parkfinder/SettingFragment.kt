package com.example.parkfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        settingBtn1.setOnClickListener {
            QueryHistory().show(fragmentManager!!, "queryHistry")
        }
        settingBtn2.setOnClickListener {
            DevFeedback().show(fragmentManager!!, "devfeedback")
        }
        settingBtn3.setOnClickListener {
            ThemeSetting().show(fragmentManager!!, "themeSetting")
        }
        settingBtn4.setOnClickListener {
            dataCorrectionReq().show(fragmentManager!!, "datacorrectionrequest")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

}
