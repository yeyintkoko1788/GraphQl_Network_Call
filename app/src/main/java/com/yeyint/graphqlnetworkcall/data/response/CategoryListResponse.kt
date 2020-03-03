package com.yeyint.graphqlnetworkcall.data.response

import com.google.gson.annotations.SerializedName
import com.yeyint.graphqlnetworkcall.data.vo.CategoryListVO

class CategoryListResponse{
    @SerializedName("data")
    var categoryListVO: CategoryListVO? = null
}