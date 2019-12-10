package com.vira.echsan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.ProfileRepository
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    val getProfile = repository.getProfile()
    val _loginInfo = MutableLiveData<List<String>>()

    val getProfileResult = switchMap(_loginInfo) {
        if (it.size >= 1) {
            repository.getProfileResult(it[0], it[1])
        } else {
            MutableLiveData()
        }
    }

    val profile_ by lazy { repository.getProfileResult("biasa@gmail.com", "1234567") }
}

