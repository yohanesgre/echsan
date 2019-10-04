package com.vira.echsan.model

data class UmrohResponse(
    val status: String,
    val code: Int,
    val message: String,
    val data: DataUmroh
)

data class DataUmroh(
    val rows: Int,
    val results: Int,
    val res: List<ResUmroh>
)

data class ResUmroh(
    val id: String,
    val namaPaket: String,
    val tipePaket: String,
    val harga: String,
    val point: String,
    val icon: String
)