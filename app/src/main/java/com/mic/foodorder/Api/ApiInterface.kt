package com.mic.foodorder.Api


import com.mic.foodorder.Model.TownShipX
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("setup/township")
    fun getTownship(): Call<TownShipX>

}