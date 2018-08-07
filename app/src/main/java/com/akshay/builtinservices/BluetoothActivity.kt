package com.akshay.builtinservices

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_bluetooth.*

class BluetoothActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        var bAdapter = BluetoothAdapter.getDefaultAdapter()

        var status = bAdapter.isEnabled
        if (status == true){
            sw.isChecked = true
        }
        else{
            sw.isChecked = false
        }

        sw.setOnCheckedChangeListener { compoundButton, b ->
            if (b== true)
                bAdapter.enable()
            else
                bAdapter.disable()

        }

        var list = mutableListOf<String>()
        var mya = ArrayAdapter<String>(this@BluetoothActivity,android.R.layout.simple_list_item_single_choice,list)
        lv.adapter = mya

        b1.setOnClickListener {
            bAdapter.startDiscovery()
            var filter = IntentFilter()
            filter.addAction(BluetoothDevice.ACTION_FOUND)
            registerReceiver(object : BroadcastReceiver() {
                override fun onReceive(p0: Context?, p1: Intent?) {
                    var device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    list.add(device.name + "/n" + device.address)
                    mya.notifyDataSetChanged()
                }
            }, filter)
        }

    }
}
