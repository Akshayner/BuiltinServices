package com.akshay.builtinservices

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sensor.*

class SensorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)
        
        var sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        
        var sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        
        sm.registerListener(object : SensorEventListener {
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }

            override fun onSensorChanged(p0: SensorEvent?) {
                var value = p0!!.values
                x.text = value[0].toString()
                y.text = value[1].toString()
            }
            
        },sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }
}
