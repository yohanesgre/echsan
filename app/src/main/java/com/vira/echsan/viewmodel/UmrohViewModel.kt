package com.vira.echsan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.PaketUmroh
import com.vira.echsan.data.entities.PaketUmrohRepository
import javax.inject.Inject

class UmrohViewModel @Inject constructor(
    private val repository: PaketUmrohRepository
): ViewModel(){
    fun searchUmrohs(tipe:String, kota:String, tanggal: String): LiveData<List<PaketUmroh>> = repository.searchPakets(tipe, kota, tanggal)
    val umrohs: LiveData<List<PaketUmroh>> = repository.getPakets()

    var tipeUmroh = arrayOf("Umroh Reguler", "Umroh VIP")
    var kotaUmroh = arrayOf("Surabaya", "Jakarta", "Malang")
    var tglUmroh = arrayOf("Oktober 2019", "November 2019")
    var jamaahUmroh = arrayOf(1,2,3,4,5)

}