package com.vira.echsan.data.entities.SubPaketUmroh

import com.google.gson.annotations.SerializedName

//@Entity(tableName = "category")
data class Category(
    //@PrimaryKey @ColumnInfo(name = "id")//@SerializedName("id")
    val cat_id: Int,
    @SerializedName("name")
    val cat_name: String,
    @SerializedName("description")
    val cat_description: String,
    @SerializedName("status")
    val cat_status: Int,
    @SerializedName("created_at")
    val cat_created_at: String,
    @SerializedName("updated_at")
    val cat_updated_at: String
)