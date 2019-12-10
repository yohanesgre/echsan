package com.vira.echsan.data.entities

import com.vira.echsan.api.BaseDataSource
import com.vira.echsan.api.LoginService
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(private val service: LoginService) :
    BaseDataSource() {
    suspend fun login(email: String, password: String) =
        getResult { service.login(email, password) }
}
