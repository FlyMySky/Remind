package com.skwen.remind.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.LogUtils
import com.skwen.remind.bean.Record
import com.skwen.remind.greendao.DaoManager
import com.skwen.remind.utils.NotificationUtils
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

            LogUtils.d("year:$year   mouth:$mouth   day:$day   hour:$hour   minute:$minute")

            val list = DaoManager.getInstance().getArgsRecord(true, false)

            if (list.isNotEmpty()) {
                list.forEach { record: Record ->
                    val time = System.currentTimeMillis()
                    if (time >= record.remindDate) {
                        val hms = record.remindTime.split(":")
                        val week = getWeek()
                        if (hms[0].toInt() == hour && hms[1].toInt() == minute) {

                            LogUtils.d("week:$week   hour:$hour   minute:$minute")

                            if (record.cycles.contains(week)) {
                                NotificationUtils.showNotify(context!!.applicationContext, record)
                            } else {
                                NotificationUtils.showNotifyOnce(context!!.applicationContext, record)
                            }

                        }
                    }
                }
            }

        }

    }


    private fun getWeek(): String {
        val cal = Calendar.getInstance()
        val i = cal.get(Calendar.DAY_OF_WEEK)
        when (i) {
            1 -> return "周日"
            2 -> return "周一"
            3 -> return "周二"
            4 -> return "周三"
            5 -> return "周四"
            6 -> return "周五"
            7 -> return "周六"
        }
        return ""
    }
}