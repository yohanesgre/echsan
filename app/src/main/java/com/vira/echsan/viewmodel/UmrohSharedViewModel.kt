package com.vira.echsan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.PaketUmroh

class UmrohSharedViewModel : ViewModel() {

    private val selectedPaket = MutableLiveData<PaketUmroh>()
    val SelectedPaket: LiveData<PaketUmroh> = selectedPaket

    fun UpdateSelectedPaket(item: PaketUmroh) {
        selectedPaket.value = item
    }

    val listOfTanggal = listOf(12, 13, 14, 15, 16, 17, 18, 19, 20, 22)
    val listOfFasilitas = listOf("Visa Umroh", "Catering", "Zam-Zam", "Perlengkapan", "Test", "Test", "Test")
    val listOfRencanaPerjalanan = listOf(
        listOf("1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sit amet sapien dignissim, consequat eros."),
        listOf("2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sit amet sapien dignissim, consequat eros."),
        listOf("3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sit amet sapien dignissim, consequat eros."))
    val progressCheckoutDesc = arrayOf("Pemesanan", "Pembayaran", "Selesai")
}