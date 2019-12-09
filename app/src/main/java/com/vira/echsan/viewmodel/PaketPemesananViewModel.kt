package com.vira.echsan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PaketPemesananViewModel @Inject constructor() : ViewModel() {
    private val _jumlahJamaah = MutableLiveData<Int>().apply {
        value = 0
    }
    val jumlahJamaah: LiveData<Int> = _jumlahJamaah

    fun addJumlahJamaah() {
        _jumlahJamaah.value = _jumlahJamaah.value?.plus(1)
    }

    fun minusJumlahJamaah() {
        _jumlahJamaah.value = _jumlahJamaah.value?.minus(1)
    }

}