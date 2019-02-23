package com.skwen.remind.settings

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.skwen.remind.R
import com.skwen.remind.adapter.SettingsAdapter
import com.skwen.remind.base.BaseActivity
import com.skwen.remind.bean.MineSettings
import kotlinx.android.synthetic.main.activity_mine_settings.*
import kotlinx.android.synthetic.main.main_toolbar_layout.*

/**
 * 作者：vicent
 * 时间：2019/2/23
 */
class MineSettingsActivity : BaseActivity() {

    private lateinit var topAdapter: SettingsAdapter
    private var topList: MutableList<MineSettings> = mutableListOf()

    private lateinit var bottomAdapter: SettingsAdapter
    private var bottomList: MutableList<MineSettings> = mutableListOf()

    override fun getLayoutRes(): Int {
        return R.layout.activity_mine_settings
    }

    override fun initViews() {

        titleBar.text = getText(R.string.action_settings)

        leftBar.setOnClickListener {
            finish()
        }

        topRecyclerView.layoutManager = LinearLayoutManager(this)
        topAdapter = SettingsAdapter(topList)
        topRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        topRecyclerView.setOnItemClickListener { itemView, position ->
            val item = topList[position]
            ToastUtils.showShort(item.content)
        }
        topRecyclerView.adapter = topAdapter


        bottomRecyclerView.layoutManager = LinearLayoutManager(this)
        bottomAdapter = SettingsAdapter(bottomList)
        bottomRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        bottomRecyclerView.setOnItemClickListener { itemView, position ->
            val item = bottomList[position]
            ToastUtils.showShort(item.content)
        }
        bottomRecyclerView.adapter = bottomAdapter
    }

    override fun loadData() {

        topList.addAll(MineSettingLoad.loadTopData(this))
        topAdapter.notifyDataSetChanged()

        bottomList.addAll(MineSettingLoad.loadBottomData(this))
        bottomAdapter.notifyDataSetChanged()

    }
}