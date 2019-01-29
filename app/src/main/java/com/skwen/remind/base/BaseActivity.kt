package com.skwen.remind.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * 作者：vicent
 * 时间：2019/1/29
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        loadData()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun loadData()
}