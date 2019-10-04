package com.vira.echsan.model

data class BeliPaketResponse(
    val status: String,
    val code: Int,
    val message: String,
    val data: DataBeliPaket
)

data class DataBeliPaket(
    val idTransaksi: Int
)