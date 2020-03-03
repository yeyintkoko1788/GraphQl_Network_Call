package com.yeyint.graphqlnetworkcall.mvp.view

import com.yeyint.graphqlnetworkcall.CategoryLisQuery

interface CategoryListView : BaseView{
    fun displayList(categoryList : List<CategoryLisQuery.Category>)
    fun navigateToCategoryDetail(categoryId: Int)
    fun refreshList()
}