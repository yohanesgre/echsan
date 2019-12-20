package com.vira.echsan.features.umroh.data.datasource

import com.vira.echsan.api.BaseDataSource
import com.vira.echsan.api.UmrohService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class PaymentRemoteDataSource @Inject constructor(private val service: UmrohService) :
    BaseDataSource() {

    suspend fun fetchPayment(
        text: Map<String, RequestBody>,
        paidFile: MultipartBody.Part
    ) = getResult { service.postPayment(text, paidFile) }
}
