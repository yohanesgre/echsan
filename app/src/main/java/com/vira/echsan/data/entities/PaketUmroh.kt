package com.vira.echsan.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paket_umrohs")
data class PaketUmroh (
    @PrimaryKey @ColumnInfo(name = "id") val paketId: Int,
    val name: String,
    val tipe: String,
    val travel: String,
    val penerbangan: String,
    val tanggal: String,
    val durasi: String,
    val harga: String,
    val hotel: String,
    val point: Int,
    val kuota: Int,
    val lokasi: String
    ) {
    override fun toString(): String = name +"_"+ paketId
}

