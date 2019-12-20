package com.vira.echsan.features.umroh.data

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody

data class Payment(
    @SerializedName("text")
    val text: HashMap<String, RequestBody>,
    @SerializedName("paid_file")
    var paidFile: MultipartBody.Part?
)