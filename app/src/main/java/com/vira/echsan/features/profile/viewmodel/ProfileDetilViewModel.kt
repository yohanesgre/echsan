package com.vira.echsan.features.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vira.echsan.features.profile.data.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProfileDetilViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    val getProfile = repository.getProfile()

    fun logout() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}

