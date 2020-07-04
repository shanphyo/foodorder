package com.mic.foodorder.Api

import com.mic.foodorder.Model.category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryApiInterface {
    @GET("category")
    fun getCategory(

    ): Call<category>
}