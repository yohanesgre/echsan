package com.vira.echsan.data.entities.SubPaketUmroh

import com.google.gson.annotations.SerializedName

data class TravelVendor(
    //@PrimaryKey
    @SerializedName("id")
    val trav_id: Int,
    @SerializedName("name")
    val trav_name: String,
    @SerializedName("description")
    val trav_description: String,
    @SerializedName("status")
    val trav_status: Int,
    @SerializedName("created_at")
    val trav_createdAt: String,
    @SerializedName("updated_at")
    val trav_updatedAt: String
)