package com.mic.foodorder.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mic.foodorder.Api.CategoryApi
import com.mic.foodorder.Model.RestaurantX
import com.mic.foodorder.Model.restaurant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TownShipViewModel ():ViewModel(){
    var resta :MutableLiveData<List<RestaurantX>> = MutableLiveData()
    var loadError: MutableLiveData<Boolean> =MutableLiveData(
    )
    var loading:MutableLiveData<Boolean> = MutableLiveData()

    //Getter
    fun getResta(ts:String):LiveData<List<RestaurantX>> =resta
    fun getError() :LiveData<Boolean> =loadError
    fun getloading():LiveData<Boolean> =loading
    private val cApi:CategoryApi = CategoryApi()

    //Setter
    fun loadRestaurantbytownship(ts:String){
        loading.value=true
        val apiCall=cApi.getRestaurantbytownship(ts)
        apiCall.enqueue(object : Callback<restaurant>{
            override fun onFailure(call: Call<restaurant>, t: Throwable) {
                loadError.value=true
                loading.value=false
            }

            override fun onResponse(call: Call<restaurant>, response: Response<restaurant>) {
                response.isSuccessful.let {
                    loading.value=false
                    val resultlist :List<RestaurantX> = response.body()?.restaurants?: emptyList()
                    resta.value=resultlist
                }
            }

        })
    }

    fun loadAllRestaurant(){
        loading.value=true
        val apiCall=cApi.getAllRestaurant()
        apiCall.enqueue(object :Callback<restaurant>{
            override fun onFailure(call: Call<restaurant>, t: Throwable) {
                loadError.value=true
                loading.value=false

            }

            override fun onResponse(call: Call<restaurant>, response: Response<restaurant>) {
                response.isSuccessful.let {
                    loading.value=false
                    val resultlist :List<RestaurantX> = response.body()?.restaurants?: emptyList()
                    resta.value=resultlist
                }
            }

        })
    }
}