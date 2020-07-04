package com.mic.foodorder.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  insert(card:Card)

    @Query("select * from Card")
    fun getAllCard():LiveData<List<Card>>

    @Ignore
    @SuppressWarnings
    @Query("select * from Card")
    fun getTotal():List<Card>

    @Query("delete from Card")
    fun getDelete()
}