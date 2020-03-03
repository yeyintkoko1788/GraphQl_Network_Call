package com.yeyint.graphqlnetworkcall.data.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.yeyint.graphqlnetworkcall.CategoryLisQuery

class CategoryModel private constructor(context: Context) : BaseModel(context) {
    companion object {
        private var INSTANCE: CategoryModel? = null
        fun getInstance(): CategoryModel {
            if (INSTANCE == null) {
                throw RuntimeException("CategoryModel is being invoke before initializing")
            }

            val i = INSTANCE
            return i!!
        }

        fun initCategoryModel(context: Context) {
            INSTANCE = CategoryModel(context)
        }
    }

    private var categoryListLiveData: MutableLiveData<List<CategoryLisQuery.Category>>? = null

    fun loadCategoryList(categoryListLD: MutableLiveData<List<CategoryLisQuery.Category>>,
                         errorLD: MutableLiveData<String>){
        categoryListLiveData = MutableLiveData()
        mApolloClient?.query(
            CategoryLisQuery
                .builder()
                .build())
            ?.enqueue(object : ApolloCall.Callback<CategoryLisQuery.Data>(){
                override fun onFailure(e: ApolloException) {
                    Log.e("Apollo call : " , e.localizedMessage)
                    Log.e("Apollo Call " , e.cause.toString())
                    errorLD.postValue(e.message + " Error ")
                }

                override fun onResponse(response: Response<CategoryLisQuery.Data>) {
                    Log.d("Apollo Call : ", "Response: " + response.data()?.categories());
                    if (!response.data()?.categories().isNullOrEmpty()){
                        categoryListLD.postValue(response.data()?.categories())
                        categoryListLiveData!!.postValue(response.data()?.categories())
                    }else{
                        errorLD.postValue("Empty List")
                    }
                }

            })
    }

    fun getCategoryList(): LiveData<List<CategoryLisQuery.Category>> {
        return categoryListLiveData!!
    }
}