package com.vira.echsan.api.resp

import com.google.gson.annotations.SerializedName

data class JamaahResp(
    val id: Int,
    @SerializedName("transaction_id")
    val transactionId: Int,
    val gender: String,
    @SerializedName("birth_place")
    val birthPlace: String,
    @SerializedName("birth_date")
    val birthDate: String,
    val address: String,
    val RT: String,
    val RW: String,
    val kelurahan: String,
    val district: String,
    @SerializedName("pos_code")
    val posCode: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val phone: String
)