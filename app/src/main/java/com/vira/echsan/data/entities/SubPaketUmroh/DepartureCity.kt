package com.vira.echsan.data.entities.SubPaketUmroh

import com.google.gson.annotations.SerializedName

data class DepartureCity(
    // @PrimaryKey
    @SerializedName("id")
    val city_id: Int,
    @SerializedName("name")
    val city_name: String,
    @SerializedName("description")
    val city_description: String,
    @SerializedName("status")
    val city_status: Int,
    @SerializedName("created_at")
    val city_createdAt: String,
    @SerializedName("updated_at")
    val city_updatedAt: String
)