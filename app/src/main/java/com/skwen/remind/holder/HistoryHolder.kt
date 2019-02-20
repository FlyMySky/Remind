package com.skwen.remind.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.skwen.remind.R

/**
 * 作者：vicent
 * 时间：2019/1/30
 */
class HistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var itemCheckBox = itemView.findViewById<CheckBox>(R.id.itemCheckBox)
    var itemLevel = itemView.findViewById<ImageView>(R.id.itemLevel)
    var itemRemindTime = itemView.findViewById<TextView>(R.id.itemRemindTime)
    var itemRemindContent = itemView.findViewById<TextView>(R.id.itemRemindContent)

}