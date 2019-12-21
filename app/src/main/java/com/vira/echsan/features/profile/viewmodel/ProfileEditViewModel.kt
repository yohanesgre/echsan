package com.vira.echsan.features.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.vira.echsan.features.profile.data.ProfileRepository
import javax.inject.Inject


class ProfileEditViewModel @Inject constructor(
    repository: ProfileRepository
) : ViewModel() {
    val getProfile = repository.getProfile()
}

