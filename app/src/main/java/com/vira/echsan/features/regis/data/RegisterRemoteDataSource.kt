package com.vira.echsan.features.regis.data

import com.vira.echsan.api.BaseDataSource
import com.vira.echsan.api.LoginService
import javax.inject.Inject

class RegisterRemoteDataSource @Inject constructor(private val service: LoginService) :
    BaseDataSource() {
    suspend fun register(name: String, email: String, phone: String, nik: String) =
        getResult { service.register(name, email, phone, nik, 4) }
}
