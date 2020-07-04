package com.mic.foodorder.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mic.foodorder.Api.CategoryApi
import com.mic.foodorder.Model.Menu
import com.mic.foodorder.Model.menus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MFCViewModel : ViewModel(){
    var topmenus: MutableLiveData<List<Menu>> = MutableLiveData()
    var loadError:MutableLiveData<Boolean> = MutableLiveData()
    var loading:MutableLiveData<Boolean> = MutableLiveData()

    //Getter
    fun getTopmenus(t:Int): LiveData<List<Menu>> =topmenus
    fun getError() :LiveData<Boolean> = loadError
    fun getLoading(): LiveData<Boolean> =loading

private val cApi:CategoryApi = CategoryApi()

    //Setter
    fun loadgetMenus(t :Int){
        loading.value=true
        val apicall=cApi.getMenusfromCategory(t)
        apicall.enqueue(object  : Callback<menus>{
            override fun onFailure(call: Call<menus>, t: Throwable) {
                loadError.value=true
                loading.value=false
            }

            override fun onResponse(call: Call<menus>, response: Response<menus>) {
                response.isSuccessful.let {
                    loading.value=false
                    val resultlist:List<Menu> = response.body()?.menus?: emptyList()
                    topmenus.value=resultlist
                }
            }

        })
    }

}