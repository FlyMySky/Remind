package com.skwen.remind.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.skwen.remind.R

/**
 * 作者：vicent
 * 时间：2019/1/30
 */
class SettingsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var itemIconRes = itemView.findViewById<ImageView>(R.id.itemIconRes)
    var itemContent = itemView.findViewById<TextView>(R.id.itemContent)
    var itemRightRes = itemView.findViewById<ImageView>(R.id.itemRightRes)

}