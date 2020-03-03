package com.yeyint.graphqlnetworkcall.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yeyint.graphqlnetworkcall.R
import com.yeyint.graphqlnetworkcall.delegate.CategoryListDelegate
import com.yeyint.graphqlnetworkcall.viewholder.BaseViewHolder
import com.yeyint.graphqlnetworkcall.viewholder.CategoryViewHolder

class CategoryAdapter(context: Context, private var delegate: CategoryListDelegate) : BaseRecyclerAdapter<CategoryViewHolder, CategoryListQuery.Category>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CategoryListQuery.Category> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_category, parent, false)
        return CategoryViewHolder(view, delegate)
    }
}