package com.skwen.remind.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*

/**
 * 作者：vicent
 * 时间：2019/1/30
 */
class PickTimeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action

        if (!action.isNullOrEmpty() && action?.equals(Intent.ACTION_TIME_TICK)!!) {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val mouth = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)



        }

    }

}