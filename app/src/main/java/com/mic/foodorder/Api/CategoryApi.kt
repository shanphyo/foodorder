package com.mic.foodorder.Api

import com.mic.foodorder.Model.category
import com.mic.foodorder.Model.menus
import com.mic.foodorder.Model.restaurant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class CategoryApi {
    private val categoryApiInterface :CategoryApiInterface
    private val townShipApiInterface: TownShipApiInterface
    private  val menApiInterface: menusApiInterface

    companion object{
        const val BASE_URL="http://food-delivery-shwe-sin-soe.khaingthinkyi.me/api/setup/"
    }
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        categoryApiInterface = retrofit.create(CategoryApiInterface::class.java)
        townShipApiInterface = retrofit.create(TownShipApiInterface::class.java)
        menApiInterface=retrofit.create(menusApiInterface::class.java)
    }
    fun getCategory(): Call<category>{
        return categoryApiInterface.getCategory()
    }
    fun getRestaurantbytownship(ts:String):Call<restaurant>{
        return  townShipApiInterface.getRestaurantByTownship(ts)
    }
    fun getAllRestaurant():Call<restaurant>{
        return townShipApiInterface.getAllRestaurant()
    }
    fun getMenus(t:Int):Call<menus>{
        return menApiInterface.gettMenus(t)

    }
    fun getMenusfromCategory(t:Int):Call<menus>{
        return menApiInterface.getmenufromcategory(t)
    }
}