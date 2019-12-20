package com.vira.echsan.features.home.viewmodel

import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.PromoRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: PromoRepository
) : ViewModel(){
    val promoSets by lazy {
        repository.getPromos()
    }

    val getPromoList by lazy {
        promoSets.value!!.toList()
    }

}