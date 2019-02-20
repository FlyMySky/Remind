package com.skwen.remind.history

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.skwen.remind.R
import com.skwen.remind.adapter.HistoryAdapter
import com.skwen.remind.base.BaseFragment
import com.skwen.remind.bean.Record
import com.skwen.remind.greendao.DaoManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * 作者：vicent
 * 时间：2019/1/26
 */
class HistoryFragment : BaseFragment() {

    /**
     * 0 已完成 1 进行中
     */
    private var page = 0

    private lateinit var mAdapter: HistoryAdapter
    private var mList: MutableList<Record> = mutableListOf()

    private var isOpen: Boolean = false
    private var isOver: Boolean = true

    override fun getLayoutRes(): Int {
        return R.layout.fragment_history
    }

    override fun initViews() {

        mRecyclerView.layoutManager = LinearLayoutManager(this.context)
        mAdapter = HistoryAdapter(mList)
        mRecyclerView.adapter = mAdapter
        mRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.mHistoryOver -> {
                    page = 0
                    historyRight.visibility = View.VISIBLE
                    isOpen = false
                    isOver = true
                    loadData()
                }

                R.id.mHistoryDoing -> {
                    page = 1
                    historyRight.visibility = View.GONE
                    isOpen = true
                    isOver = false
                    loadData()
                }
            }
        }

    }

    override fun loadData() {
        Observable
            .just("")
            .observeOn(Schedulers.newThread())
            .map { t -> DaoManager.getInstance().getArgsRecord(isOpen, isOver) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t ->
                mList.clear()
                mList.addAll(t)
                mAdapter.notifyDataSetChanged()
            }
    }
}