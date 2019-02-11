package com.skwen.remind.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.blankj.utilcode.util.TimeUtils
import com.skwen.remind.R
import com.skwen.remind.bean.Record
import com.skwen.remind.holder.TodayHolder
import java.text.SimpleDateFormat

/**
 * 作者：vicent
 * 时间：2019/1/30
 */
class TodayAdapter(private var list: MutableList<Record>) : RecyclerView.Adapter<TodayHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): TodayHolder {

        return TodayHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.fragment_today_list_item,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TodayHolder, position: Int) {
        val item = list[position]
        when {
            item.level == 0 -> holder.itemLevel.setImageResource(R.drawable.ic_add_level_unselected)
            item.level == 1 -> holder.itemLevel.setImageResource(R.drawable.ic_add_level_middle_unselected)
            item.level == 2 -> holder.itemLevel.setImageResource(R.drawable.ic_add_level_important_unselected)
        }
        holder.itemRemindTime.text = TimeUtils.millis2String(item.remindDate, SimpleDateFormat("HH:mm"))
        holder.itemRemindContent.text = item.content
    }
}