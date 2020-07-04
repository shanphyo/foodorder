package com.mic.foodorder.Api

import com.mic.foodorder.Model.menus
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface menusApiInterface{
    @GET("menus_by_restaurant")
    fun gettMenus(
        @Query("restaurant_id")restaurant_id:Int
    ): Call<menus>

    @GET("menu_by_category?")
    fun getmenufromcategory(
        @Query("category_id")category_id:Int
    ):Call<menus>
}