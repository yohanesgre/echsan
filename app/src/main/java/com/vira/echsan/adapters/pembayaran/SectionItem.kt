package com.vira.echsan.adapters.pembayaran

data class SectionItem(
    val title: String,
    val color: Int,
    val itemList: List<ChildItem>,
    var initailized: Boolean = false
)