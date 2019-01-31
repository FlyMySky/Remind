package com.skwen.remind.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.skwen.remind.MainActivity
import com.skwen.remind.R
import com.skwen.remind.bean.Record
import com.skwen.remind.greendao.DaoManager


/**
 * 作者：vicent
 * 时间：2019/1/31
 */
class NotificationUtils {


    companion object {

        private lateinit var channel: NotificationChannel

        const val ACTION_CLICK = "skwen.intent.action.CLICK"
        const val ACTION_DELETE = "skwen.intent.action.DELETE"

        fun showNotify(context: Context, record: Record) {
            notify(context, record)
        }

        fun showNotifyOnce(context: Context, record: Record) {

            if (record.cycles.isEmpty()) {
                notify(context, record)
                record.isOver = true
                record.isOpen = false
                DaoManager.getInstance().updateRecord(record)
            }
        }


        private fun notify(context: Context, record: Record) {
            if (record.isOpen && !record.isOver) {
                val manager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    channel = NotificationChannel(
                        "chat",
                        "聊天",
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    channel.setSound(null, null)
                    channel.vibrationPattern = null

                    manager.createNotificationChannel(channel)
                    //创建点击通知时发送的广播
                    val intent = Intent(context, MainActivity::class.java)
                    intent.action = ACTION_CLICK
                    val pi = PendingIntent.getActivity(context, 0, intent, FLAG_CANCEL_CURRENT)
                    //创建删除通知时发送的广播
//                    val deleteIntent = Intent(context, MainActivity::class.java)
//                    deleteIntent.action = ACTION_DELETE
//                    val deletePendingIntent = PendingIntent.getActivity(context, 0, deleteIntent, 0)
                    //创建通知
                    val nb = NotificationCompat.Builder(context, channel.id)
                        //设置通知左侧的小图标
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //设置通知标题
                        .setContentTitle("Remind")
                        //设置通知内容
                        .setContentText(record.content)
                        //设置点击通知后自动删除通知
                        .setAutoCancel(true)
                        //设置点击通知时的响应事件
                        .setContentIntent(pi)
                        //设置删除通知时的响应事件
//                        .setContentIntent(deletePendingIntent)
                        .build()
                    //发送通知
                    manager.notify(1, nb)

                } else {

                    val intent = Intent(context, MainActivity::class.java)
                    intent.action = ACTION_CLICK
                    val pi = PendingIntent.getActivity(context, 0, intent, 0)

                    val builder = NotificationCompat.Builder(context)
                        //设置小图标
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //设置通知标题
                        .setContentTitle("Remind")
                        //设置通知内容
                        .setContentText(record.content)
                        .setContentIntent(pi)
                    //设置删除通知时的响应事件
//                        .setContentIntent(deletePendingIntent)
                    manager.notify(1, builder.build())
                }
            }
        }

    }


}