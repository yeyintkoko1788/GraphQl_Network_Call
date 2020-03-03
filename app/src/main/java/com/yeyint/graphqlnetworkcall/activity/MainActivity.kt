package com.yeyint.graphqlnetworkcall.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeyint.graphqlnetworkcall.CategoryLisQuery
import com.yeyint.graphqlnetworkcall.R
import com.yeyint.graphqlnetworkcall.adapter.CategoryAdapter
import com.yeyint.graphqlnetworkcall.components.EmptyViewPod
import com.yeyint.graphqlnetworkcall.mvp.presenter.CategoryListPresenter
import com.yeyint.graphqlnetworkcall.mvp.view.CategoryListView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empty_view_pod.*
import kotlinx.android.synthetic.main.toolbar_pin.*

class MainActivity : BaseActivity(), CategoryListView {

    private var mAdapter: CategoryAdapter? = null
    private lateinit var mPresenter: CategoryListPresenter

    override fun getlayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun setUpContents(savedInstanceState: Bundle?) {
        mPresenter = ViewModelProviders.of(this).get(CategoryListPresenter::class.java)
        mPresenter.initPresenter(this)
        mAdapter = CategoryAdapter(applicationContext, mPresenter)

        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, true)
        linearLayoutManager.stackFromEnd = true

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val emptyView = findViewById<EmptyViewPod>(R.id.vpEmpty)
        emptyView.visibility = View.GONE
        emptyView.setEmptyData(R.drawable.error, "Empty List")
        btn_refresh_empty.setOnClickListener { mPresenter.onTapRefresh() }

        rv_category.setHasFixedSize(true)
        rv_category.layoutManager = linearLayoutManager
        rv_category.adapter = mAdapter

        mPresenter.errorLD.observe(this,
            Observer<String> { errorMsg: String? ->
                progressBar.visibility = View.GONE
                emptyView.setEmptyData(R.drawable.error, errorMsg!!)
                rv_category.setEmptyView(emptyView)
                emptyView.visibility = View.VISIBLE
            })

        mPresenter.loadCategoryList()
        observeCategoryData()
    }

    private fun observeCategoryData(){
        mPresenter.onUiReady(1).observe(this, Observer<List<CategoryLisQuery.Category>> { categoryList: List<CategoryLisQuery.Category>? ->
            run {
                displayList(categoryList!!)
            }
        })
    }

    override fun displayList(categoryList: List<CategoryLisQuery.Category>) {
        mAdapter!!.clearData()
        progressBar.visibility = View.GONE
        mAdapter!!.appendNewData(categoryList)
    }

    override fun navigateToCategoryDetail(categoryId: Int) {

    }

    override fun refreshList() {
        mPresenter.loadCategoryList()
        observeCategoryData()
    }
}
