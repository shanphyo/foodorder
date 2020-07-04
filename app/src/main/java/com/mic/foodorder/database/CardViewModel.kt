package com.mic.foodorder.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.mic.roomfragment.database.CardDatabase
import kotlinx.coroutines.launch

class CardViewModel (application: Application):AndroidViewModel(application){
    private  val resposity:CardResposity
    lateinit var totalist:List<Card>
    val allCard:LiveData<List<Card>>
    init {
        val cardDao = CardDatabase.getDatabase(application).cardDao()
        resposity= CardResposity(cardDao)
        allCard =resposity.allCard
        totalist= resposity.getTotal()

    }

    fun getTotal():Double=totalist.map {
        it.cprice
    }.sum()

    fun insert(card:Card) =viewModelScope.launch { resposity.insert(card) }
    fun getDelete(){
        resposity.getDelete()
    }
}
