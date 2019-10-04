package com.vira.echsan.model

data class PaketMemberResp(
    val status: String,
    val code: Int,
    val message: String,
    val data: List<DataPaketMember>
)

data class DataPaketMember(
    val id: Int,
    val namaPaket: String,
    val hargaPaket: Int
)