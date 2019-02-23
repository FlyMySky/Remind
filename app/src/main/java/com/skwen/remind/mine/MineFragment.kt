package com.skwen.remind.mine

import android.content.Intent
import com.skwen.remind.R
import com.skwen.remind.base.BaseFragment
import com.skwen.remind.settings.MineSettingsActivity
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * 作者：vicent
 * 时间：2019/1/26
 */
class MineFragment : BaseFragment() {


    override fun getLayoutRes(): Int {
        return R.layout.fragment_mine
    }

    override fun initViews() {

        mineSettings.setOnClickListener {
            startActivity(Intent(activity, MineSettingsActivity::class.java))
        }

    }

    override fun loadData() {

    }
}