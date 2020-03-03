package com.yeyint.graphqlnetworkcall.app

import android.app.Application
import com.yeyint.graphqlnetworkcall.data.models.CategoryModel

class GraphQlNetworkCallApp : Application() {

    override fun onCreate() {
        super.onCreate()

        CategoryModel.initCategoryModel(this)
    }
}