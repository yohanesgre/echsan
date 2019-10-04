package com.vira.echsan.model

data class RiwayatTransaksiResponse(
    val status: String,
    val code: Int,
    val message: String,
    val data: DataRiwayatTransaksi
)

data class DataRiwayatTransaksi(
    val rows: Int,
    val results: Int,
    val res: List<ResRiwayatTransaksi>
)

data class ResRiwayatTransaksi(
    val id: String,
    val namaProduk: String,
    val harga: String,
    val tanggalBayar: String,
    val status: String
)