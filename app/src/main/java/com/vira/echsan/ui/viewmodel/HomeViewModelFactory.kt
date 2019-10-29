package com.vira.echsan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vira.echsan.data.entities.PromoRepository

class HomeViewModelFactory(
    private val repository: PromoRepository)
    :ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>) = HomeViewModel(repository) as T
}