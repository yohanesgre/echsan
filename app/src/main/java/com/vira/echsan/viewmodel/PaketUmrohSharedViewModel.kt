package com.vira.echsan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.PaketUmroh

class PaketUmrohSharedViewModel : ViewModel(){
    private val _selectedPaket = MutableLiveData<PaketUmroh>()
    val selectedPaket:LiveData<PaketUmroh> = _selectedPaket

    fun setSelectedPaket(paket:PaketUmroh){
        _selectedPaket.value = paket
    }

    private val _searchPaket = MutableLiveData<List<String>>()
    val searchPaket:LiveData<List<String>> = _searchPaket

    fun searchPaket(tipe:String, kota:String, tanggal:String){
        _searchPaket.value = listOf(tipe, kota, tanggal)
    }

    private val _selectedTanggal = MutableLiveData<Int>()
    val selectedTanggal:LiveData<Int> = _selectedTanggal

    val posSelectedTanggal = MutableLiveData<Int>()

    fun setSelectedTanggal(tanggal:Int) {
        _selectedTanggal.value = tanggal
    }

    val listOfTanggal = listOf(12, 13, 14, 15, 16, 17, 18, 19, 20, 22)
    val listOfFasilitas = listOf("Visa Umroh", "Catering", "Zam-Zam", "Perlengkapan")
}