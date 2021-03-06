package com.vira.echsan.api.resp

import com.google.gson.annotations.SerializedName

data class TransactionResp(
    @SerializedName("id")
    val transactionId: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("total_payment")
    val totalPayment: Double,
    @SerializedName("paid_payment")
    val paidPayment: Double,
    @SerializedName("remain_payment")
    val remainPayment: Double,
    @SerializedName("transaction_code")
    val transactionCode: String,
    @SerializedName("paid_done")
    val paidDone: Int,
    @SerializedName("people_amount")
    val peopleAmount: String,
    @SerializedName("update_at")
    val updatedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("jamaah")
    val listOfJamaah: List<JamaahResp>,
    @SerializedName("transaction_detail")
    val listOfTransactionDetail: List<TransactionDetailResp>,
    @SerializedName("product")
    val product: ProductResp
)