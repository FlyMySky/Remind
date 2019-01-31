package com.skwen.remind

import android.content.Intent
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.skwen.remind.adapter.MainPagerAdapter
import com.skwen.remind.base.BaseActivity
import com.skwen.remind.history.HistoryFragment
import com.skwen.remind.mine.MineFragment
import com.skwen.remind.service.RemindService
import com.skwen.remind.today.TodayFragment
import com.skwen.remind.utils.IntentProvider
import com.skwen.remind.utils.PUtils
import com.skwen.remind.utils.PUtils.REQUEST_IGNORE_BATTERY_CODE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    private var fragmentList: List<Fragment> = arrayListOf(HistoryFragment(), TodayFragment(), MineFragment())

    private lateinit var adapter: MainPagerAdapter

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun loadData() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!PUtils.getInstance().isIgnoringBatteryOptimizations(this)) {
                PUtils.getInstance().gotoSettingIgnoringBatteryOptimizations(this)
                return
            }
        }

        val intent = Intent(this, RemindService::class.java)
        startService(intent)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.historyBtn -> {
                    mainViewPager.currentItem = 0
                }
                R.id.todayBtn -> {
                    mainViewPager.currentItem = 1
                }
                R.id.mineBtn -> {
                    mainViewPager.currentItem = 2
                }
            }
        }

        fab.setOnClickListener { view ->
            startActivityForResult(IntentProvider.getAddRemindIntent(this), IntentProvider.code)
        }
        adapter = MainPagerAdapter(supportFragmentManager, fragmentList)
        mainViewPager.adapter = adapter
        mainViewPager.currentItem = 1
        mainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                when (p0) {
                    0 -> {
                        radioGroup.check(R.id.historyBtn)
                    }
                    1 -> {
                        radioGroup.check(R.id.todayBtn)
                    }
                    2 -> {
                        radioGroup.check(R.id.mineBtn)
                    }
                }
            }

        })
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun switchGroup(isShow: Boolean) {
        if (isShow) {
            radioGroup.visibility = View.VISIBLE
        } else {
            radioGroup.visibility = View.GONE
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IntentProvider.code) {

        }

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IGNORE_BATTERY_CODE) {
                Log.d("Hello World!", "开启省电模式成功")
                loadData()
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (requestCode == REQUEST_IGNORE_BATTERY_CODE) {
                Toast.makeText(this, "请用户开启忽略电池优化~", Toast.LENGTH_LONG).show();
            }
        }
    }

}
