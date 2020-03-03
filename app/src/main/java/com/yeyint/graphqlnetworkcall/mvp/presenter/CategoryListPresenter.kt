package com.yeyint.graphqlnetworkcall.mvp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yeyint.graphqlnetworkcall.CategoryLisQuery
import com.yeyint.graphqlnetworkcall.data.models.CategoryModel
import com.yeyint.graphqlnetworkcall.delegate.CategoryListDelegate
import com.yeyint.graphqlnetworkcall.mvp.view.CategoryListView

class CategoryListPresenter : BasePresenter<CategoryListView>(), CategoryListDelegate{
    override fun onTapCategoryItem() {
    }

    private var mCategoryListLD : MutableLiveData<List<CategoryLisQuery.Category>>? = null

    override fun initPresenter(mView: CategoryListView) {
        super.initPresenter(mView)
        mCategoryListLD = MutableLiveData()
    }

    fun loadCategoryList(){
        CategoryModel.getInstance().loadCategoryList(mCategoryListLD!!, mErrorLD )
    }

    fun onTapRefresh(){
        mView.refreshList()
    }

    fun onUiReady(pageNumber: Int) : LiveData<List<CategoryLisQuery.Category>> {
        return CategoryModel.getInstance().getCategoryList()
    }

    fun onTapEmptyViewButtonRefresh(){
        mView.refreshList()
    }
}