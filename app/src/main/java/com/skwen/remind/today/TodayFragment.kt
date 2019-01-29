package com.skwen.remind.today

import android.view.View
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.jaeger.library.StatusBarUtil
import com.skwen.remind.R
import com.skwen.remind.base.BaseFragment
import com.skwen.remind.greendao.DaoManager
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


    override fun getLayoutRes(): Int {
        return R.layout.fragment_today
    }

    override fun initViews() {

        StatusBarUtil.setTransparent(activity)
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

        mTextMonthDay.setOnClickListener {
            if (!mCalendarLayout.isExpand) {
                mCalendarLayout.expand()
                return@setOnClickListener
            }
            mCalendarView.showYearSelectLayout(mYear)
            mTextLunar.visibility = View.GONE
            mTextYear.visibility = View.GONE
            mTextMonthDay.text = mYear.toString()
            mActivity.switchGroup(false)
        }

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

        mCalendarView.setOnCalendarSelectListener(object : CalendarView.OnCalendarSelectListener {
            override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                if (isClick) {
                    setTitle(calendar?.day.toString(), calendar?.month.toString(), mCalendarView.curYear.toString())
                    mActivity.switchGroup(true)
                }
            }

            override fun onCalendarOutOfRange(calendar: Calendar?) {

            }

        })

        mCalendarView.setOnYearChangeListener { year -> mTextMonthDay.text = year.toString() }

    }

    override fun loadData() {

        Observable
            .just(mCalendarView.curYear.toString() + "," + mCalendarView.curMonth.toString() + "," + mCalendarView.curDay.toString())
            .observeOn(Schedulers.newThread())
            .map { t -> DaoManager.getInstance().all }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t -> var size = t?.size }


    }


    private fun setTitle(day: String, month: String, year: String) {
        mTextYear.text = year
        mYear = year.toInt()
        mTextMonthDay.text = month + "月" + day + "日"
        mTextLunar.text = day
        mTextCurrentDay.text = mCalendarView.curDay.toString()
    }

}