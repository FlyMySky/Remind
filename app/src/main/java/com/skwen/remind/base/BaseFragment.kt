package com.skwen.remind.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skwen.remind.MainActivity

/**
 * 作者：vicent
 * 时间：2019/1/26
 */
public abstract class BaseFragment : Fragment() {

    private var isVisibleToUser: Boolean = false
    private var isViewCreated: Boolean = false

    lateinit var mActivity: MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = (this!!.activity as MainActivity?)!!
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (isVisibleToUser && isViewCreated) {
            loadData()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        initViews()
        if (isVisibleToUser && isViewCreated) {
            loadData()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }


    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initViews()

    abstract fun loadData()
}