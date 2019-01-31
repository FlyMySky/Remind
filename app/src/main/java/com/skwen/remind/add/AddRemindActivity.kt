package com.skwen.remind.add

import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils
import com.jaeger.library.StatusBarUtil
import com.jzxiang.pickerview.listener.OnDateSetListener
import com.skwen.remind.R
import com.skwen.remind.base.BaseActivity
import com.skwen.remind.bean.Record
import com.skwen.remind.dialog.CycleSelectDialog
import com.skwen.remind.greendao.DaoManager
import com.skwen.remind.utils.TimePickerUtils
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat

/**
 * 作者：vicent
 * 时间：2019/1/29
 */
class AddRemindActivity : BaseActivity() {


    override fun getLayoutRes(): Int {
        return R.layout.activity_add
    }

    override fun loadData() {
        StatusBarUtil.setTransparent(this)
        StatusBarUtil.setLightMode(this)
        addLeftBtn.setOnClickListener {
            finish()
        }

        addConform.setOnClickListener {
            val record = Record()
            val millSecends = mouthAndDay.tag as Long?
            val list = remindCycleTime.tag as MutableList<String>?
            record.saveTime = TimeUtils.getNowMills()
            record.content = remindContent.text.toString()
            record.cycles = list
            record.remindDate = millSecends
            record.remindTime = remindTime.text as String?
            record.yearTime = TimeUtils.millis2String(millSecends!!, SimpleDateFormat("yyyy")).toInt()
            record.mouthTime = TimeUtils.millis2String(millSecends, SimpleDateFormat("MM")).toInt()
            record.dayTime = TimeUtils.millis2String(millSecends, SimpleDateFormat("dd")).toInt()
            record.isOpen = true
            record.isOver = false
            if (list!!.size < 7) {
                record.isEveryDay = false
            } else if (list.size == 7) {
                record.isEveryDay = true
            }
            //0 normal 1 middle 2 important
            when {
                levelNormal.isChecked -> record.level = 0
                levelMiddle.isChecked -> record.level = 1
                levelImportant.isChecked -> record.level = 2
                else -> record.level = 0
            }

            if (DaoManager.getInstance().insertRecord(record) > 0) {
                ToastUtils.showShort("添加成功！")
                finish()
            } else {
                ToastUtils.showShort("添加失败！")
            }
        }

        mouthAndDay.setOnClickListener {
            TimePickerUtils.selectMonthDayHourMinuteTime(this, supportFragmentManager,
                OnDateSetListener { timePickerView, millseconds ->
                    mouthAndDay.text = TimeUtils.millis2String(millseconds, SimpleDateFormat("MM月dd日"))
                    remindTime.text = TimeUtils.millis2String(millseconds, SimpleDateFormat("HH:mm"))
                    mouthAndDay.tag = millseconds
                })
        }

        remindTime.setOnClickListener {
            TimePickerUtils.selectMonthDayHourMinuteTime(this, supportFragmentManager,
                OnDateSetListener { timePickerView, millseconds ->
                    mouthAndDay.text = TimeUtils.millis2String(millseconds, SimpleDateFormat("MM月dd日"))
                    remindTime.text = TimeUtils.millis2String(millseconds, SimpleDateFormat("HH:mm"))
                    mouthAndDay.tag = millseconds
                })
        }

        remindCycleTime.setOnClickListener {

            CycleSelectDialog.getInstance(object : CycleSelectDialog.CallBack {
                override fun onSelect(list: List<String>) {
                    remindCycleTime.tag = list
                    if (list.isEmpty()) {
                        remindCycleTime.text = getText(R.string.add_week_not_repeat)
                        return
                    }

                    val stringBuilder = StringBuilder()

                    list.forEach { s ->
                        run {
                            if (stringBuilder.isNotEmpty()) {
                                stringBuilder.append(",")
                            }
                            stringBuilder.append(s)
                        }
                    }

                    remindCycleTime.text = stringBuilder.toString()

                }

            }).show(supportFragmentManager, "cycle")
        }

        levelNormal.setOnClickListener {
            if (levelNormal.isChecked) {
                levelMiddle.isChecked = false
                levelImportant.isChecked = false
            }
        }
        levelMiddle.setOnClickListener {
            if (levelMiddle.isChecked) {
                levelNormal.isChecked = false
                levelImportant.isChecked = false
            }
        }
        levelImportant.setOnClickListener {
            if (levelImportant.isChecked) {
                levelMiddle.isChecked = false
                levelNormal.isChecked = false
            }
        }
    }


}