package com.vira.echsan.model


data class UmrohDetailResponse(
    val status: String,
    val code: Int,
    val message: String,
    val data: DataUmrohDetail
)

data class DataUmrohDetail(
    val id: String,
    val namaPaket: String,
    val tipePaket: String,
    val keterangan: String,
    val harga: String,
    val point: String,
    val icon: String
)