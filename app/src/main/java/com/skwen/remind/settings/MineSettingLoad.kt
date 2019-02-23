package com.skwen.remind.settings

import android.content.Context
import com.skwen.remind.R
import com.skwen.remind.bean.MineSettings

/**
 * 作者：vicent
 * 时间：2019/2/23
 */
object MineSettingLoad {


    fun loadTopData(context: Context): MutableList<MineSettings> {

        val list: MutableList<MineSettings> = mutableListOf()
        list.clear()
        val topMenu = context.resources.getStringArray(R.array.mine_setting_top_menu)
        topMenu.iterator().forEach { s ->
            val item = MineSettings()
            item.content = s
            item.iconRes = R.drawable.ic_add_level_unselected
            item.rightRes = R.drawable.ic_today_navigate_right
            item.type = 0
            list.add(item)
        }
        return list
    }


    fun loadBottomData(context: Context): MutableList<MineSettings> {

        val list: MutableList<MineSettings> = mutableListOf()
        list.clear()
        val bottomMenu = context.resources.getStringArray(R.array.mine_setting_bottom_menu)
        bottomMenu.iterator().forEach { s ->
            val item = MineSettings()
            item.content = s
            item.iconRes = R.drawable.ic_add_level_unselected
            item.type = 1
            list.add(item)
        }
        return list
    }


}