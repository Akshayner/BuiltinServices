package com.akshay.builtinservices

import android.content.Context
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_wifi.*

class WifiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi)

        var wm = getSystemService(Context.WIFI_SERVICE) as WifiManager

        var status = wm.wifiState
        if (status == 0){
            Toast.makeText(this@WifiActivity,"Wifi is disabled",Toast.LENGTH_LONG).show()
            // wifi off
        }
        else if(status == 3){
            Toast.makeText(this@WifiActivity,"Wifi is enabled",Toast.LENGTH_LONG).show()
            //wifi on
        }

        b1.setOnClickListener {
            var result = wm.scanResults
            var list = mutableListOf<String>()

            for (device in result){
            list.add(device.SSID+System.lineSeparator()+device.frequency)
            }

            var mya = ArrayAdapter<String>(this@WifiActivity,android.R.layout.simple_list_item_single_choice,list)
            lv.adapter = mya
        }

        b2.setOnClickListener{
            var result =wm.configuredNetworks
            var list = mutableListOf<String>()

            for (device in result){
                list.add(device.SSID+System.lineSeparator()+device.status)
            }

            var mya = ArrayAdapter<String>(this@WifiActivity,android.R.layout.simple_list_item_single_choice,list)
            lv.adapter = mya
        }

    }
}
