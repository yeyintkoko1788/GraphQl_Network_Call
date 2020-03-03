package com.yeyint.graphqlnetworkcall.viewholder

import android.view.View
import com.yeyint.graphqlnetworkcall.delegate.CategoryListDelegate
import kotlinx.android.synthetic.main.view_item_category.view.*

class CategoryViewHolder(itemView: View?, private var delegate: CategoryListDelegate) : BaseViewHolder<CategoryListQuery.Category>(itemView!!) {
    override fun setData(data: CategoryListQuery.Category) {
        itemView.tv_title.text = data.name()
    }

    override fun onClick(v: View?) {

    }
}