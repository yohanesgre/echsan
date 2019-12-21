package com.vira.echsan.api.resp

import com.google.gson.annotations.SerializedName

data class RegisterResp(
    /*"name": "Reyhan Alphard Savero",
"email": "reyhanalphardsavero@gmail.com",
"default_password": "DFZ2RT9S",
"phone": "081233339066",
"role_id": "4",
"point": "0",
"code": "REYH223035",
"token": "echsan:wxHzi0k9x2-hcKuhOB9TXrTEOQJ39GgKLp37qm501",
"updated_at": "2019-12-20 22:30:36",
"created_at": "2019-12-20 22:30:36",
"id": 1000000000*/
    val name: String,
    val email: String,
    @SerializedName("default_password")
    val defaulPassword: String,
    val phone: String,
    @SerializedName("role_id")
    val roleId: Int,
    val point: Int,
    val code: String,
    val token: String,
    @SerializedName("update_at")
    val updatedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int
)