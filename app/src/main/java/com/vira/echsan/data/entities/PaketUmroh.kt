package com.vira.echsan.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vira.echsan.data.entities.SubPaketUmroh.*

@Entity(
    tableName = "paket_umrohs"/*,
   foreignKeys = arrayOf(
        ForeignKey(entity = Category::class, parentColumns = arrayOf("id"), childColumns = arrayOf("category_id"), onDelete = ForeignKey.NO_ACTION),
        ForeignKey(entity = DepartureCity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("departure_city_id"), onDelete = ForeignKey.NO_ACTION),
        ForeignKey(entity = DeparturePlane::class, parentColumns = arrayOf("id"), childColumns = arrayOf("departure_plane_id"), onDelete = ForeignKey.NO_ACTION),
        ForeignKey(entity = ProductCategory::class, parentColumns = arrayOf("id"), childColumns = arrayOf("product_category_id"), onDelete = ForeignKey.NO_ACTION),
        ForeignKey(entity = ReturnPlane::class, parentColumns = arrayOf("id"), childColumns = arrayOf("return_plane_id"), onDelete = ForeignKey.NO_ACTION),
        ForeignKey(entity = TravelVendor::class, parentColumns = arrayOf("id"), childColumns = arrayOf("travel_vendor_id"), onDelete = ForeignKey.NO_ACTION)
    )*/
)
data class PaketUmroh (
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
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
    @Embedded
    val category: Category,
    @Embedded
    val travel_vendor: TravelVendor,
    @Embedded
    val departure_city: DepartureCity,
    @Embedded
    val product_category: ProductCategory,
    @Embedded
    val departure_plane: DeparturePlane,
    @Embedded
    val return_plane: ReturnPlane
)