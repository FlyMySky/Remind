package com.skwen.remind.utils

import android.content.Context
import android.support.v4.app.FragmentManager
import com.jzxiang.pickerview.TimePickerDialog
import com.jzxiang.pickerview.data.Type
import com.jzxiang.pickerview.listener.OnDateSetListener
import com.skwen.remind.R

/**
 * 作者：vicent
 * 时间：2019/1/30
 */
object TimePickerUtils {

    private var tenYears = 10L * 365 * 1000 * 60 * 60 * 24L

    fun selectAllTime(context: Context, fragmentManager: FragmentManager, listener: OnDateSetListener) {
        val mDialogAll = TimePickerDialog
            .Builder()
            .setCallBack(listener)
            .setCancelStringId("cancel")
            .setSureStringId("Sure")
            .setTitleStringId("TimePicker")
            .setYearText("Year")
            .setMonthText("Month")
            .setDayText("Day")
            .setHourText("Hour")
            .setMinuteText("Minute")
            .setCyclic(false)
            .setMinMillseconds(System.currentTimeMillis())
            .setMaxMillseconds(System.currentTimeMillis() + tenYears)
            .setCurrentMillseconds(System.currentTimeMillis())
            .setThemeColor(context.resources.getColor(R.color.timepicker_dialog_bg))
            .setType(Type.ALL)
            .setWheelItemTextNormalColor(context.resources.getColor(R.color.timetimepicker_default_text_color))
            .setWheelItemTextSelectorColor(context.resources.getColor(R.color.timepicker_toolbar_bg))
            .setWheelItemTextSize(12)
            .build()
        mDialogAll.show(fragmentManager, "all")
    }

    fun selectYearMouthTime(context: Context, fragmentManager: FragmentManager, listener: OnDateSetListener) {
        val mDialogYearMouth = TimePickerDialog
            .Builder()
            .setType(Type.YEAR_MONTH)
            .setThemeColor(context.resources.getColor(R.color.timepicker_dialog_bg))
            .setCallBack(listener)
            .build()
        mDialogYearMouth.show(fragmentManager, "year_mouth")
    }

    fun selectYearMouthDayTime(context: Context, fragmentManager: FragmentManager, listener: OnDateSetListener) {
        val mDialogYearMouth = TimePickerDialog
            .Builder()
            .setType(Type.YEAR_MONTH_DAY)
            .setThemeColor(context.resources.getColor(R.color.timepicker_dialog_bg))
            .setCallBack(listener)
            .build()
        mDialogYearMouth.show(fragmentManager, "year_mouth_day")
    }

    fun selectMonthDayHourMinuteTime(context: Context, fragmentManager: FragmentManager, listener: OnDateSetListener) {
        val mDialogYearMouth = TimePickerDialog
            .Builder()
            .setType(Type.MONTH_DAY_HOUR_MIN)
            .setThemeColor(context.resources.getColor(R.color.timepicker_dialog_bg))
            .setCallBack(listener)
            .build()
        mDialogYearMouth.show(fragmentManager, "mouth_day_hour_min")
    }

    fun selectHourMinuteTime(context: Context, fragmentManager: FragmentManager, listener: OnDateSetListener) {
        val mDialogYearMouth = TimePickerDialog
            .Builder()
            .setType(Type.HOURS_MINS)
            .setThemeColor(context.resources.getColor(R.color.timepicker_dialog_bg))
            .setCallBack(listener)
            .build()
        mDialogYearMouth.show(fragmentManager, "hour_min")
    }


}