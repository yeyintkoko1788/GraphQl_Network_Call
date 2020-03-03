package com.yeyint.graphqlnetworkcall.data.models

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.yeyint.graphqlnetworkcall.utils.AppConstants.Companion.BASE_URL
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


abstract class BaseModel protected constructor(context: Context) {

    protected var mApolloClient: ApolloClient? = null

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        mApolloClient = ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build()
    }
}