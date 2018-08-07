package com.akshay.builtinservices

import android.content.Context
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_wifi.*

class WifiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi)

        var wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        var status = wm.wifiState
        if (status == 0 || status == 1){
            swi.isChecked = false
        }
        else if(status == 2 || status == 3){
            swi.isChecked = true
        }

        swi.setOnCheckedChangeListener { compoundButton, b ->
            wm.setWifiEnabled(b)
        }

        b1.setOnClickListener {
            var result = wm.scanResults
            var list = mutableListOf<String>()

            for (device in result){
            list.add(device.SSID+"/n"+device.frequency)
            }

            var mya = ArrayAdapter<String>(this@WifiActivity,android.R.layout.simple_list_item_single_choice,list)
            lv.adapter = mya
        }

        b2.setOnClickListener{
            var result = wm.configuredNetworks
            var list = mutableListOf<String>()

            for (device in result){
                list.add(device.SSID+"/n"+device.status)
            }

            var mya = ArrayAdapter<String>(this@WifiActivity,android.R.layout.simple_list_item_single_choice,list)
            lv.adapter = mya
        }

    }
}
