package com.vira.echsan.features.umroh.viewmodel

import androidx.lifecycle.ViewModel
import com.vira.echsan.R
import com.vira.echsan.adapters.pembayaran.ChildItem
import com.vira.echsan.adapters.pembayaran.ImageMetodePembayaranItem
import com.vira.echsan.adapters.pembayaran.MetodePembayaranItem
import com.vira.echsan.adapters.pembayaran.SectionItem
import com.vira.echsan.features.umroh.data.repo.InputJamaahRepository
import javax.inject.Inject

class PaketPembayaranViewModel @Inject constructor(
    private val repository: InputJamaahRepository
) : ViewModel() {
    val tipePembayaranMap: List<ChildItem> by lazy {
        val list = ArrayList<ChildItem>()
        list.add(ChildItem("Uang Muka - Rp. 5.000.000 per Jamaah", 5000000.0))
        list.add(ChildItem("Uang Muka - Rp. 10.000.000 per Jamaah", 10000000.0))
        return@lazy list
    }

    val tipePembayaranSection = SectionItem(
        "Pilih Tipe Pembayaran",
        R.color.colorPrimary, tipePembayaranMap
    )
    val imgMetodePembayaran = listOf<ImageMetodePembayaranItem>(
        ImageMetodePembayaranItem(R.drawable.ic_dropdown),
        ImageMetodePembayaranItem(R.drawable.ic_dropdown_clicked)
    )
    val metodePembayaran = listOf<MetodePembayaranItem>(
        MetodePembayaranItem("Transfer Bank", imgMetodePembayaran)
    )

}