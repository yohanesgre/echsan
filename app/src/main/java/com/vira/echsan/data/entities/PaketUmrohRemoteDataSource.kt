package com.vira.echsan.data.entities

import com.vira.echsan.api.BaseDataSource
import com.vira.echsan.api.UmrohService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class PaketUmrohRemoteDataSource @Inject constructor(private val service: UmrohService) :
    BaseDataSource() {

    suspend fun fetchPaketUmrohs() = getResult { service.getPaketUmrohs() }
    suspend fun fetchPaketUmroh(id: Int) = getResult { service.getPaketUmroh(id) }
}
