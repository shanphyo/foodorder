package com.mic.foodorder.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mic.foodorder.Api.TownshipApi
import com.mic.foodorder.Model.TownShipX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TownShipXViewModel : ViewModel() {

    private var township: MutableLiveData<TownShipX> = MutableLiveData()

    fun getTownship(): LiveData<TownShipX> = township

    fun loadTownship() {

        val townshipApi: TownshipApi = TownshipApi()

        val call = townshipApi.getTownships()

        call.enqueue(object : Callback<TownShipX> {
            override fun onFailure(call: Call<TownShipX>, t: Throwable) {
                Log.d("Result Error>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<TownShipX>, response: Response<TownShipX>) {
                response?.isSuccessful.let {
                    val townshipResult = TownShipX(response.body()?.townships?: emptyList())
                    township.value = townshipResult
                    Log.d("Result township>>>>>>", townshipResult.toString())
                    Log.d("Result Response>>>>>>", response.body().toString())

                }
            }

        })
    }
}
