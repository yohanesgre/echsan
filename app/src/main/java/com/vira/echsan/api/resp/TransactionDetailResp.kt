package com.vira.echsan.api.resp

import com.google.gson.annotations.SerializedName

class TransactionDetailResp(
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("transaction_id")
    val transactionId: Int,
    @SerializedName("paid")
    val paid: Double,
    val accept: Int,
    @SerializedName("accept_by")
    val acceptBy: Int,
    @SerializedName("paid_file")
    val paidFile: String,
    @SerializedName("update_at")
    val updatedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("url_payment")
    val urlPayment: String
) {
    /*"id": 3,
    "user_id": 4,
    "transaction_id": 3,
    "paid": 22500000,
    "accept": 1,
    "accept_by": 999999999,
    "paid_file": "",
    "created_at": "2019-12-05 04:12:18",
    "updated_at": "2019-12-05 04:12:31",
    "url_payment": "https://app.sandbox.midtrans.com/snap/v2/vtweb/beb84172-33c7-4261-a67e-77391223573b"*/
}