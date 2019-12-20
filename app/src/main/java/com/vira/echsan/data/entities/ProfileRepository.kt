package com.vira.echsan.data.entities

import com.vira.echsan.data.dao.ProfileDao
import com.vira.echsan.data.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val profileDao: ProfileDao,
    private val remoteDataSource: ProfileRemoteDataSource
) {

    fun getProfile() = profileDao.getProfile()
    fun getProfileResult(email: String, password: String) = resultLiveData(
        databaseQuery = { profileDao.getProfile() },
        networkCall = { remoteDataSource.login(email, password) },
        saveCallResult = {
            if (it.id != 0)
                profileDao.insert(it)
        })

    companion object {
        @Volatile
        private var instance: ProfileRepository? = null

        fun getInstance(dao: ProfileDao, remoteDataSource: ProfileRemoteDataSource) =
            instance ?: synchronized(this) {
                instance ?: ProfileRepository(dao, remoteDataSource).also {
                    instance = it
                }
            }
    }
}