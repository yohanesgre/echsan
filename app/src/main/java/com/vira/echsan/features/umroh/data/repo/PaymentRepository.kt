package com.vira.echsan.features.umroh.data.repo

import com.vira.echsan.features.umroh.data.Payment
import com.vira.echsan.features.umroh.data.datasource.PaymentRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepository @Inject constructor(
    private val remoteDataSource: PaymentRemoteDataSource
) {
    suspend fun postPayment(
        payment: Payment
    ) = remoteDataSource.fetchPayment(payment.text, payment.paidFile!!)

    companion object {
        @Volatile
        private var instance: PaymentRepository? = null

        fun getInstance(
            remoteDataSource: PaymentRemoteDataSource
        ) =
            instance ?: synchronized(this) {
                instance ?: PaymentRepository(remoteDataSource).also {
                    instance = it
                }
            }
    }
}