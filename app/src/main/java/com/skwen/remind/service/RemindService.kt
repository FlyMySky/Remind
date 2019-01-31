package com.skwen.remind.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import com.skwen.remind.receiver.PickTimeReceiver

/**
 * 作者：vicent
 * 时间：2019/1/31
 */
class RemindService : Service() {

    private lateinit var pickTimeReceiver: PickTimeReceiver

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onCreate() {
        super.onCreate()
        pickTimeReceiver = PickTimeReceiver()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        onStarts(intent, startId)
        return Service.START_NOT_STICKY
    }

    private fun onStarts(intent: Intent?, startId: Int) {
        if (pickTimeReceiver == null) {
            pickTimeReceiver = PickTimeReceiver()
        }
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_TIME_TICK)
        this.registerReceiver(pickTimeReceiver, filter)
    }


    override fun onDestroy() {
        super.onDestroy()
        if (pickTimeReceiver != null)
            this.unregisterReceiver(pickTimeReceiver)
    }

}