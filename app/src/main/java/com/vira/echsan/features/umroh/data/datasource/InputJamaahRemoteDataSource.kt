package com.vira.echsan.features.umroh.data.datasource

import com.vira.echsan.api.BaseDataSource
import com.vira.echsan.api.UmrohService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class InputJamaahRemoteDataSource @Inject constructor(private val service: UmrohService) :
    BaseDataSource() {

    suspend fun fetchInputJamaah(
        productId: Int,
        userId: Int,
        peopleAmount: Int,
        fullName: ArrayList<String>,
        gender: ArrayList<String>,
        birthPlace: ArrayList<String>,
        birthDate: ArrayList<String>,
        address: ArrayList<String>,
        RT: ArrayList<Int>,
        RW: ArrayList<Int>,
        kelurahan: ArrayList<String>,
        district: ArrayList<String>,
        city: ArrayList<String>,
        province: ArrayList<String>,
        posCode: ArrayList<Int>,
        phone: ArrayList<String>
    ) = getResult {
        service.postInputJemaah(
            productId,
            userId,
            peopleAmount,
            fullName,
            gender,
            birthPlace,
            birthDate,
            address,
            RT,
            RW,
            kelurahan,
            district,
            city,
            province,
            posCode,
            phone
        )
    }
}
