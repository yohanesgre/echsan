package com.vira.echsan.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promos")
data class Promo (
    @PrimaryKey @ColumnInfo(name = "id") val promoId:Int,
    val name:String
)