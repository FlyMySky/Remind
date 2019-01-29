package com.skwen.remind.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * 作者：vicent
 * 时间：2019/1/29
 */
class MainPagerAdapter(fragmentManager: FragmentManager, private var list: List<Fragment>) :
    FragmentPagerAdapter(fragmentManager) {


    override fun getItem(p0: Int): Fragment {
        return list[p0]
    }

    override fun getCount(): Int {
        return list.size
    }
}