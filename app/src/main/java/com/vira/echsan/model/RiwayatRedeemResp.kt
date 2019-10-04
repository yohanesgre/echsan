package com.vira.echsan.model

data class RiwayatRedeemResponse(
    val status: String,
    val code: Int,
    val message: String,
    val data: DataRiwayatRedeem
)

data class DataRiwayatRedeem(
    val rows: Int,
    val results: Int,
    val res: List<ResRiwayatRedeem>
)

data class ResRiwayatRedeem(
    val id: String,
    val tanggalPengajuan: String,
    val poinDiajukan: String,
    val status: String
)