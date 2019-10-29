package com.vira.echsan.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey @ColumnInfo(name = "id") val id:Int,
    val bookingId:String,
    val name:String,
    val status:Int,
    val travel:String,
    val date:String,
    val duration:String
)