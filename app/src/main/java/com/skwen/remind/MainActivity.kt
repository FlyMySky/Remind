package com.skwen.remind

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import com.blankj.utilcode.util.FragmentUtils
import com.jaeger.library.StatusBarUtil
import com.skwen.remind.history.HistoryFragment
import com.skwen.remind.mine.MineFragment
import com.skwen.remind.today.TodayFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragmentList: List<Fragment> = arrayListOf(HistoryFragment(), TodayFragment(), MineFragment())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.setTransparent(this)

        switchFragment(1)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.historyBtn -> {
                    switchFragment(0)
                }
                R.id.todayBtn -> {
                    switchFragment(1)
                }
                R.id.mineBtn -> {
                    switchFragment(2)
                }
            }
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "ADD", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun switchFragment(index: Int) {
        FragmentUtils.replace(supportFragmentManager, fragmentList[index], R.id.mainContainer)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
