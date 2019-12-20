package com.vira.echsan.features.umroh.data.repo

import com.vira.echsan.features.umroh.data.InputJamaah
import com.vira.echsan.features.umroh.data.datasource.InputJamaahRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InputJamaahRepository @Inject constructor(
    private val remoteDataSource: InputJamaahRemoteDataSource
) {
    suspend fun postInputJamaah(
        productId: Int,
        inputJamaah: InputJamaah
    ) = remoteDataSource.fetchInputJamaah(
        productId,
        inputJamaah.userId!!,
        inputJamaah.peopleAmount!!,
        inputJamaah.fullName!!,
        inputJamaah.gender!!,
        inputJamaah.birthPlace!!,
        inputJamaah.birthDate!!,
        inputJamaah.address!!,
        inputJamaah.RT!!,
        inputJamaah.RW!!,
        inputJamaah.kelurahan!!,
        inputJamaah.district!!,
        inputJamaah.city!!,
        inputJamaah.province!!,
        inputJamaah.posCode!!,
        inputJamaah.phone!!
    )

    companion object {
        @Volatile
        private var instance: InputJamaahRepository? = null

        fun getInstance(
            remoteDataSource: InputJamaahRemoteDataSource
        ) =
            instance ?: synchronized(this) {
                instance ?: InputJamaahRepository(remoteDataSource).also {
                    instance = it
                }
            }
    }
}