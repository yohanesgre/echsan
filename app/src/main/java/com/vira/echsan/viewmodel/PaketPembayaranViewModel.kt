package com.vira.echsan.viewmodel

import androidx.lifecycle.ViewModel
import com.vira.echsan.R
import com.vira.echsan.adapters.pembayaran.ImageMetodePembayaranItem
import com.vira.echsan.adapters.pembayaran.MetodePembayaranItem
import com.vira.echsan.adapters.pembayaran.SectionItem
import javax.inject.Inject

class PaketPembayaranViewModel @Inject constructor() : ViewModel() {
    val tipePembayaranSection = SectionItem(
        "Pilih Tipe Pembayaran",
        R.color.colorPrimary,
        arrayListOf("Uang Muka - 5.000.000 per Jamaah", "Uang Muka - 10.000.000 per Jamaah")
    )
    val imgMetodePembayaran = listOf<ImageMetodePembayaranItem>(
        ImageMetodePembayaranItem(R.drawable.ic_dropdown),
        ImageMetodePembayaranItem(R.drawable.ic_dropdown_clicked)
    )
    val metodePembayaran = listOf<MetodePembayaranItem>(
        MetodePembayaranItem("Transfer Bank", imgMetodePembayaran)
    )
}