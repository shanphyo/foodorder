package com.mic.foodorder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Card {
    @PrimaryKey(autoGenerate = true)
    var cid:Int=0
    @ColumnInfo
    var cname:String=""
    @ColumnInfo
    var cprice:Double=0.0

}