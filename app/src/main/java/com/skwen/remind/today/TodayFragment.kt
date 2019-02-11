package com.skwen.remind.today

import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import com.blankj.utilcode.util.ToastUtils
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.jaeger.library.StatusBarUtil
import com.skwen.remind.R
import com.skwen.remind.adapter.TodayAdapter
import com.skwen.remind.base.BaseFragment
import com.skwen.remind.bean.Record
import com.skwen.remind.greendao.DaoManager
import com.skwen.remind.utils.MyContentProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_today.*

/**
 * 作者：vicent
 * 时间：2019/1/26
 */
class TodayFragment : BaseFragment() {

    private var mYear: Int = 0

    private lateinit var mAdapter: TodayAdapter

    private var mList: MutableList<Record> = mutableListOf()

    private var mHandler = Handler(Looper.getMainLooper())

    private val mContentObserver = object : ContentObserver(mHandler) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            loadData()
        }
    }


    override fun getLayoutRes(): Int {
        return R.layout.fragment_today
    }

    override fun initViews() {
        StatusBarUtil.setTransparent(activity)
        StatusBarUtil.setLightMode(activity)
        // 设置状态栏字体黑色
//        activity!!.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        activity?.contentResolver?.registerContentObserver(MyContentProvider.URI, true, mContentObserver)


        mAdapter = TodayAdapter(mList)

        mRecyclerView.adapter = mAdapter

        mLeftImage.setOnClickListener {

            if (mCalendarView.isYearSelectLayoutVisible) {
                mCalendarView.closeYearSelectLayout()
                mActivity.switchGroup(true)
                return@setOnClickListener
            }

            if (mCalendarLayout.isExpand) {
                mCalendarLayout.shrink()
                return@setOnClickListener
            }

            mActivity.finish()
        }

//        mTextMonthDay.setOnClickListener {
//            if (!mCalendarLayout.isExpand) {
//                mCalendarLayout.expand()
//                return@setOnClickListener
//            }
//            mCalendarView.showYearSelectLayout(mYear)
//            mTextLunar.visibility = View.GONE
//            mTextYear.visibility = View.GONE
//            mTextMonthDay.text = mYear.toString()
//            mActivity.switchGroup(false)
//        }

        flCurrent.setOnClickListener {
            mCalendarView.scrollToCurrent()
            setTitle(
                mCalendarView.curDay.toString(),
                mCalendarView.curMonth.toString(),
                mCalendarView.curYear.toString()
            )
        }

        mTextCurrentDay.setOnClickListener {
            mCalendarView.scrollToCurrent()
            setTitle(
                mCalendarView.curDay.toString(),
                mCalendarView.curMonth.toString(),
                mCalendarView.curYear.toString()
            )
        }
        setTitle(mCalendarView.curDay.toString(), mCalendarView.curMonth.toString(), mCalendarView.curYear.toString())

        setExpandState()

        mCalendarView.setOnCalendarSelectListener(object : CalendarView.OnCalendarSelectListener {
            override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                if (isClick) {
                    setTitle(calendar?.day.toString(), calendar?.month.toString(), mCalendarView.curYear.toString())
                    mActivity.switchGroup(true)
                }
            }

            override fun onCalendarOutOfRange(calendar: Calendar?) {
                ToastUtils.showShort("超出设定范围。")
            }

        })
        mCalendarView.setOnViewChangeListener {
            setExpandState()
        }
//        mCalendarView.setOnYearChangeListener { year -> mTextMonthDay.text = year.toString() }
        mCalendarView.setOnMonthChangeListener { year, month ->
            mSelectTime.text = "$year-$month"
        }
        mSelectLeft.setOnClickListener {
            mCalendarView.scrollToPre()
        }

        mSelectRight.setOnClickListener {
            mCalendarView.scrollToNext()
        }

        mExpandLayout.setOnClickListener {
            if (mCalendarLayout.isExpand) {
                mCalendarLayout.shrink()
                mExpandCalendar.setImageResource(R.drawable.ic_today_expand_more)
            } else {
                mCalendarLayout.expand()
                mExpandCalendar.setImageResource(R.drawable.ic_today_expand_less)
            }
        }
    }

    private fun setExpandState() {
        Handler().postDelayed({
            if (mCalendarLayout.isExpand) {
                mExpandCalendar.setImageResource(R.drawable.ic_today_expand_less)
            } else {
                mExpandCalendar.setImageResource(R.drawable.ic_today_expand_more)
            }
        }, 500)
    }

    override fun loadData() {

        Observable
            .just(mCalendarView.curYear.toString() + "," + mCalendarView.curMonth.toString() + "," + mCalendarView.curDay.toString())
            .observeOn(Schedulers.newThread())
            .map { t -> DaoManager.getInstance().all }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t ->
                mList.clear()
                mList.addAll(t)
                mAdapter.notifyDataSetChanged()
            }
    }


    private fun setTitle(day: String, month: String, year: String) {
//        mTextYear.text = year
        mSelectTime.text = "$year-$month"
        mYear = year.toInt()
//        mTextMonthDay.text = month + "月" + day + "日"
//        mTextLunar.text = day
        mTextCurrentDay.text = mCalendarView.curDay.toString()
    }


    override fun onDestroy() {
        super.onDestroy()
        activity?.contentResolver?.unregisterContentObserver(mContentObserver)
    }
}