package com.vira.echsan.features.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vira.echsan.features.profile.data.ProfileRepository
import javax.inject.Inject


class ProfileUbahPasswordViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    var userId: Int? = null
    val _ubahPasswordInfo = MutableLiveData<List<String>>()

    val getUbahPasswordResult = Transformations.switchMap(_ubahPasswordInfo) {
        if (it.size >= 1) {
            userId?.let { it1 -> repository.getUbahPasswordResult(it1, it[0], it[1], it[2]) }
        } else {
            MutableLiveData()
        }
    }
}

