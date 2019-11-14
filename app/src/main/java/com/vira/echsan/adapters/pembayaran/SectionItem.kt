package com.vira.echsan.adapters.pembayaran

data class SectionItem(
    val title: String,
    val color: Int,
    val itemList: List<String>,
    var initailized: Boolean = false
)