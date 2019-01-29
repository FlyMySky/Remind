package com.skwen.remind.utils

import android.content.Context
import android.content.Intent
import com.skwen.remind.add.AddRemindActivity

/**
 * 作者：vicent
 * 时间：2019/1/29
 */
object IntentProvider {

    var code = 1001

    fun getAddRemindIntent(context: Context): Intent {
        return Intent(context, AddRemindActivity::class.java)
    }


}