package com.vira.echsan.model

data class DashboardResponse(
    val status: String,
    val code: Int,
    val message: String,
    val data: DataDashboard
)

data class DataDashboard(
    val totalPoint: String,
    val totalVoucher: String
)