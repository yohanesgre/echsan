package com.vira.echsan.model

data class LoginResponse(
        val status: String,
        val code: Int,
        val message: String,
        val data: DataLogin
)

data class DataLogin(
        val token: String,
        val nama: String
)