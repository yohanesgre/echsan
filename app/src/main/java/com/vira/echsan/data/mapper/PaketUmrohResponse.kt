package com.vira.echsan.data.mapper

import com.google.gson.annotations.SerializedName

data class PaketUmrohResponse(
    val id: Int,
    val category_id: Int,
    val name: String,
    val description: String,
    val price: String,
    val point: Int,
    val image: String,
    val date_of_departure: String,
    val created_at: String,
    val updated_at: String,
    val travel_vendor_id: Int,
    val departure_city_id: Int,
    val product_category_id: Int,
    val quota: Int,
    val discount: Int,
    val slug: String,
    val date_of_return: String,
    val departure_plane_id: Int,
    val return_plane_id: Int,
    val departure_information: String,
    val return_information: String,
    val day_amount: Int,
    val makkah_hotel: String,
    val madinah_hotel: String,
    val makkah_star: Int,
    val madinah_star: Int,
    val discount_price: String,
    val category: Category,
    val travel_vendor: TravelVendor,
    val departure_city: DepartureCity,
    val product_category: ProductCategory,
    val departure_plane: DeparturePlane,
    val return_plane: ReturnPlane
)

data class Category(
    @SerializedName("id")
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

data class DepartureCity(
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

data class DeparturePlane(
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

data class ProductCategory(
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

data class ReturnPlane(
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

data class TravelVendor(
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