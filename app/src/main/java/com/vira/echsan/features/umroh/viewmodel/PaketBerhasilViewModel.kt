package com.vira.echsan.features.umroh.viewmodel

import androidx.lifecycle.ViewModel
import com.vira.echsan.features.umroh.data.repo.PaymentRepository
import javax.inject.Inject

class PaketBerhasilViewModel @Inject constructor(
    private val repository: PaymentRepository
) : ViewModel() {

}