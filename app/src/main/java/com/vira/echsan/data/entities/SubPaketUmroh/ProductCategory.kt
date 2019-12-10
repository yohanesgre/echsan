package com.vira.echsan.data.entities.SubPaketUmroh

import com.google.gson.annotations.SerializedName

data class ProductCategory(
    //@PrimaryKey
    @SerializedName("id")
    val prod_id: Int,
    @SerializedName("name")
    val prod_name: String,
    @SerializedName("description")
    val prod_description: String,
    @SerializedName("status")
    val prod_status: Int,
    @SerializedName("created_at")
    val prod_createdAt: String,
    @SerializedName("updated_at")
    val prod_updatedAt: String,
    @SerializedName("image")
    val prod_image: String
)