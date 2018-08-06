package com.akshay.builtinservices

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b1.setOnClickListener {
            val i = Intent(this@MainActivity,LocationActivity::class.java)
            startActivity(i)
        }

        b2.setOnClickListener {
            mynotify()
        }
        b3.setOnClickListener {
            val i = Intent(this@MainActivity,WifiActivity::class.java)
            startActivity(i)
        }
        b4.setOnClickListener {
            val i = Intent(this@MainActivity,SensorActivity::class.java)
            startActivity(i)
        }
        b5.setOnClickListener {
            vibrate()
        }


    } ///oncreate

    fun mynotify(){
        var nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var ncompat = NotificationCompat.Builder(this@MainActivity,"")
        ncompat.setTicker("hey")
        ncompat.setSmallIcon(R.mipmap.ic_launcher)
        ncompat.setContentTitle("hi bro")
        ncompat.setContentText("How was your day?")

        val i = Intent(this@MainActivity,MainActivity::class.java)
        var pi = PendingIntent.getActivity(this,0,i,0)

        ncompat.setContentIntent(pi)
        nm.notify(1,ncompat.build())
    } ////notify

    fun vibrate(){
        var vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vib.vibrate(10000.toLong())
    }
}
