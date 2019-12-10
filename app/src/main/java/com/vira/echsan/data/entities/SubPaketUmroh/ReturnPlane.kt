package com.vira.echsan.data.entities.SubPaketUmroh

import com.google.gson.annotations.SerializedName

data class ReturnPlane(
    //@PrimaryKey
    @SerializedName("id")
    val ret_plane_id: Int,
    @SerializedName("name")
    val ret_plane_name: String,
    @SerializedName("description")
    val ret_plane_description: String,
    @SerializedName("status")
    val ret_plane_status: Int,
    @SerializedName("created_at")
    val ret_plane_createdAt: String,
    @SerializedName("updated_at")
    val ret_plane_updatedAt: String
)