package com.vira.echsan.features.umroh.viewmodel

import androidx.lifecycle.ViewModel
import com.vira.echsan.features.profile.data.ProfileRepository
import javax.inject.Inject

class PaketPemesananViewModel @Inject constructor(
    private val repositoryProfile: ProfileRepository
) : ViewModel() {
    val dataPemesan = repositoryProfile.getProfile()
}