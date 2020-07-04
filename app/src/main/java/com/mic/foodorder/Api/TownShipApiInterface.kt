package com.mic.foodorder.Api

import com.mic.foodorder.Model.restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TownShipApiInterface{
 @GET("restaurant_by_township")
 fun getRestaurantByTownship(
     @Query("township")township:String
 ): Call<restaurant>

    @GET("restaurant")
    fun getAllRestaurant(

    ):Call<restaurant>
}