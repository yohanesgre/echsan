package com.vira.echsan.features.profile.data

import com.vira.echsan.api.BaseDataSource
import com.vira.echsan.api.LoginService
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(private val service: LoginService) :
    BaseDataSource() {
    suspend fun login(email: String, password: String) =
        getResult { service.login(email, password) }

    suspend fun ubasPassword(
        userId: Int,
        oldPassword: String,
        newPassword: String,
        newPasswordConfrim: String
    ) =
        getResult { service.ubahPassword(userId, oldPassword, newPassword, newPasswordConfrim) }
}
