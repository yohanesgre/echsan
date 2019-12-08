package com.vira.echsan.viewmodel

import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.PaketUmrohRepository
import javax.inject.Inject

class PaketUmrohViewModel @Inject constructor(
    private val repository: PaketUmrohRepository
): ViewModel(){

    var id: Int = 0
    val paketUmrohResult by lazy { repository.observePaketUmroh(id) }


    /*
    var tipeUmroh = arrayOf("Umroh Reguler", "Umroh VIP")
    var kotaUmroh = arrayOf("Surabaya", "Jakarta", "Malang")
    var tglUmroh = arrayOf("Oktober 2019", "November 2019")
    var jamaahUmroh = arrayOf(1,2,3,4,5)
    */
}