package com.vira.echsan.features.bookings.data.datasource

import com.vira.echsan.api.BaseDataSource
import com.vira.echsan.api.UmrohService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class BookingsRemoteDataSource @Inject constructor(private val service: UmrohService) :
    BaseDataSource() {
    suspend fun fetchBookingsDone(userId: Int, page: Int, pageSize: Int? = null) =
        getResult { service.postTransactionUserDone(userId, page, pageSize) }

    suspend fun fetchBookingsUndone(userId: Int, page: Int, pageSize: Int? = null) =
        getResult { service.postTransactionUserUndone(userId, page, pageSize) }

    suspend fun fetchBooking(code: String) = getResult { service.postTransactionSearch(code) }
}
