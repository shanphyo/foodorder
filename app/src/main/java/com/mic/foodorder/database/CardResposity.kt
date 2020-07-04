package com.mic.foodorder.database

import androidx.lifecycle.LiveData

class CardResposity (private val cartDao: CartDao){
    val allCard:LiveData<List<Card>> =cartDao.getAllCard()
    suspend fun  insert (card:Card){
        cartDao.insert(card)
    }
    fun getTotal():List<Card>{
        return cartDao.getTotal()
    }
    fun getDelete(){
        cartDao.getDelete()
    }
}