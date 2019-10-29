package com.vira.echsan.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.Promo
import com.vira.echsan.data.entities.PromoRepository

class HomeViewModel internal constructor(
    promoRepository: PromoRepository
) : ViewModel(){
    val promos:LiveData<List<Promo>> =  promoRepository.getPromos()
}