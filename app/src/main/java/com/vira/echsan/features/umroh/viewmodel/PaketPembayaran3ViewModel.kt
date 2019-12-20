package com.vira.echsan.features.umroh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vira.echsan.features.umroh.data.Payment
import com.vira.echsan.features.umroh.data.repo.PaymentRepository
import kotlinx.coroutines.Dispatchers
import java.io.File
import javax.inject.Inject

class PaketPembayaran3ViewModel @Inject constructor(val repository: PaymentRepository) :
    ViewModel() {
    private var _imageUploadFile = MutableLiveData<File>()
    var imageUploadFile: LiveData<File> = _imageUploadFile

    fun UpdateImageUploadFile(imageFile: File) {
        _imageUploadFile.postValue(imageFile)
    }

    val inputPayment = MutableLiveData<Payment>()

    fun setInputPayment(item: Payment) {
        inputPayment.postValue(item)
    }

    val inputPaymentResp = liveData(Dispatchers.IO) {
        if (inputPayment.value != null)
            emit(repository.postPayment(inputPayment.value!!))
    }
}