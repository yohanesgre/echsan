package com.vira.echsan.features.umroh.data.datasource

import com.vira.echsan.api.BaseDataSource
import com.vira.echsan.api.UmrohService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class PaketUmrohRemoteDataSource @Inject constructor(private val service: UmrohService) :
    BaseDataSource() {

    suspend fun fetchPaketUmrohs(page: Int, pageSize: Int? = null) =
        getResult { service.getPaketUmrohs(page, pageSize) }

    suspend fun fetchPaketUmroh(id: Int) = getResult { service.getPaketUmroh(id) }
}
