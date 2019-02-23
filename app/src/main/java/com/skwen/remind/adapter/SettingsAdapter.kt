package com.skwen.remind.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skwen.remind.R
import com.skwen.remind.bean.MineSettings
import com.skwen.remind.holder.SettingsHolder

/**
 * 作者：vicent
 * 时间：2019/2/23
 */
class SettingsAdapter(private var list: MutableList<MineSettings>) : RecyclerView.Adapter<SettingsHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): SettingsHolder {
        return SettingsHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.settings_list_item,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SettingsHolder, positon: Int) {

        val item = list[positon]

        holder.itemIconRes.setImageResource(item.iconRes)

        holder.itemContent.text = item.content

        holder.itemRightRes.setImageResource(item.rightRes)

    }
}