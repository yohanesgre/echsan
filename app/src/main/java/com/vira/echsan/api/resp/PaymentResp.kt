package com.vira.echsan.data.entities

import com.google.gson.annotations.SerializedName

data class PaymentResp(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("transaction_id")
    val transactionId: Int,
    @SerializedName("paid")
    val paid: Double,
    @SerializedName("accept")
    val accept: Int,
    @SerializedName("accept_by")
    val acceptBy: Int,
    @SerializedName("paid_file")
    val paidFile: String,
    @SerializedName("update_at")
    val updatedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val paymentId: String
)