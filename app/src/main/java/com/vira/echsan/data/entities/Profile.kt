package com.vira.echsan.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    var name: String? = null,
    var email: String,
    var email_verified_at: String? = null,
    var default_password: String,
    var phone: String? = null,
    var role_id: Int?,
    var created_at: String? = null,
    var updated_at: String? = null,
    var code: String? = null,
    var point: String? = null,
    var photo: String? = null,
    var token: String? = null,
    var aktif: Int?
)
