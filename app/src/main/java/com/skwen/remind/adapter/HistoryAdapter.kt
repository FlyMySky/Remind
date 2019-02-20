package com.skwen.remind.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.blankj.utilcode.util.TimeUtils
import com.skwen.remind.R
import com.skwen.remind.bean.Record
import com.skwen.remind.holder.HistoryHolder
import java.text.SimpleDateFormat

/**
 * 作者：vicent
 * 时间：2019/1/30
 */
class HistoryAdapter(private var list: MutableList<Record>) : RecyclerView.Adapter<HistoryHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): HistoryHolder {

        return HistoryHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.fragment_history_list_item,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val item = list[position]
        if (item.isOver) {
            holder.itemLevel.setImageResource(R.drawable.ic_history_list_item_done)
        } else {
            holder.itemLevel.setImageResource(R.drawable.ic_history_list_item_undone)
        }
        holder.itemRemindTime.text = TimeUtils.millis2String(item.remindDate, SimpleDateFormat("HH:mm"))
        holder.itemRemindContent.text = item.content
    }
}