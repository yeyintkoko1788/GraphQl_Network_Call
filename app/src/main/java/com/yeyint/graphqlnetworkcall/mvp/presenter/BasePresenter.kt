package com.yeyint.graphqlnetworkcall.mvp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yeyint.graphqlnetworkcall.mvp.view.BaseView


abstract class BasePresenter<T : BaseView> : ViewModel() {

    protected lateinit var mView: T
    protected lateinit var mErrorLD: MutableLiveData<String>

    val errorLD: LiveData<String>
        get() = mErrorLD

    open fun initPresenter(mView: T) {
        this.mView = mView
        mErrorLD = MutableLiveData()
    }
}