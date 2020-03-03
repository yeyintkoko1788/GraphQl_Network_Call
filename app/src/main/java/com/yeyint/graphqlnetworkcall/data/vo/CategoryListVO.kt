package com.yeyint.graphqlnetworkcall.data.vo

import com.google.gson.annotations.SerializedName

class CategoryListVO {

    @SerializedName("categories")
    var categoryVO: ArrayList<CategoryVO>? = null
}