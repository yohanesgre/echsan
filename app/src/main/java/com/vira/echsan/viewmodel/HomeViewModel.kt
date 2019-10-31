package com.vira.echsan.viewmodel

import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.PromoRepository
import com.vira.echsan.di.CoroutineScropeIO
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: PromoRepository
) : ViewModel(){
    val promoSets by lazy {
        repository.getPromos()
    }

    override fun onCleared() {
        super.onCleared()

    }
}