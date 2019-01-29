package com.skwen.remind.add

import com.skwen.remind.R
import com.skwen.remind.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add.*

/**
 * 作者：vicent
 * 时间：2019/1/29
 */
class AddRemindActivity : BaseActivity() {


    override fun getLayoutRes(): Int {
        return R.layout.activity_add
    }

    override fun loadData() {

        addLeftBtn.setOnClickListener {
            finish()
        }

        addConform.setOnClickListener {

        }

        mouthAndDay.setOnClickListener {

        }

        remindTime.setOnClickListener {

        }

        remindCycleTime.setOnClickListener {

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