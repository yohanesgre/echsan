package com.vira.echsan.features.regis.data

import com.vira.echsan.data.dao.ProfileDao
import com.vira.echsan.data.resultLiveData
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val profileDao: ProfileDao,
    private val remoteDataSource: RegisterRemoteDataSource
) {
    fun getRegisterResult(name: String, email: String, phone: String, nik: String) = resultLiveData(
        databaseQuery = { profileDao.getProfile() },
        networkCall = { remoteDataSource.register(name, email, phone, nik) },
        saveCallResult = {
            if (it.id != 0)
                profileDao.insert(it)
        })


    companion object {
        @Volatile
        private var instance: RegisterRepository? = null

        fun getInstance(dao: ProfileDao, remoteDataSource: RegisterRemoteDataSource) =
            instance ?: synchronized(this) {
                instance ?: RegisterRepository(dao, remoteDataSource).also {
                    instance = it
                }
            }
    }
}