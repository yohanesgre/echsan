package com.vira.echsan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vira.echsan.data.entities.BookingRepository

class BookingsChildViewModelFactory (
    private val repository: BookingRepository,
    private val status:Int)
    :ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>) = BookingsChildViewModel(repository, status) as T
}
