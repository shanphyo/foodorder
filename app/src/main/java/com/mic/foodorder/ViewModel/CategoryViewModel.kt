package com.mic.foodorder.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mic.foodorder.Api.CategoryApi
import com.mic.foodorder.Model.CategoryX
import com.mic.foodorder.Model.category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel :ViewModel(){
    var getCategory:MutableLiveData<List<CategoryX>> = MutableLiveData()
    var loadError: MutableLiveData<Boolean> = MutableLiveData()
    var loading: MutableLiveData<Boolean> =MutableLiveData()

    //Getter
    fun getCategories():LiveData<List<CategoryX>> =getCategory
    fun getError():LiveData<Boolean> =loadError
    fun getLoading():LiveData<Boolean> =loading
    private val categoryApi:CategoryApi = CategoryApi()

    //Setter
    fun getloadCategory(){
        loading.value=true
        val apiCall = categoryApi.getCategory()
        apiCall.enqueue(object :Callback<category>{
            override fun onFailure(call: Call<category>, t: Throwable) {
                loadError.value=true
                loading.value=false
            }

            override fun onResponse(call: Call<category>, response: Response<category>) {
                response.isSuccessful.let {
                    loading.value=false
                    val resultlist:List<CategoryX> = response.body()?.categories?: emptyList()
                    getCategory.value=resultlist
                }
            }

        })
    }
}