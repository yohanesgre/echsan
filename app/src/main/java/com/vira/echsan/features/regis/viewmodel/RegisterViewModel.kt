package com.vira.echsan.features.regis.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.vira.echsan.features.regis.data.RegisterRepository
import javax.inject.Inject


class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {
    val _loginInfo = MutableLiveData<List<String>>()

    val getRegisterResult = switchMap(_loginInfo) {
        if (it.size >= 1) {
            repository.getRegisterResult(it[0], it[1], it[2], it[3])
        } else {
            MutableLiveData()
        }
    }
}

