package com.vira.echsan.data.entities.SubPaketUmroh

import com.google.gson.annotations.SerializedName

data class DeparturePlane(
    //@PrimaryKey
    @SerializedName("id")
    val dep_plane_id: Int,
    @SerializedName("name")
    val dep_plane_name: String,
    @SerializedName("description")
    val dep_plane_description: String,
    @SerializedName("status")
    val dep_plane_status: Int,
    @SerializedName("created_at")
    val dep_plane_createdAt: String,
    @SerializedName("updated_at")
    val dep_plane_updatedAt: String
)