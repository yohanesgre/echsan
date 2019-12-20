package com.vira.echsan.features.umroh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vira.echsan.features.umroh.data.InputJamaah
import com.vira.echsan.features.umroh.data.repo.InputJamaahRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PaketPembayaran2ViewModel @Inject constructor(
    val repository: InputJamaahRepository
) : ViewModel() {
    var productId: Int = 0
    private val _inputJamaah = MutableLiveData<InputJamaah>()
    val inputJamaah: LiveData<InputJamaah> = _inputJamaah

    val response = liveData(Dispatchers.IO) {
        emit(repository.postInputJamaah(productId, inputJamaah.value!!))
    }

    fun setInputJamaah(item: InputJamaah) {
        _inputJamaah.postValue(item)
    }
}