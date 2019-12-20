package com.vira.echsan.features.bookings.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vira.echsan.api.resp.TransactionResp
import com.vira.echsan.data.dao.PaketUmrohDao
import com.vira.echsan.features.bookings.data.datasource.BookingsPageDataSourceFactory
import com.vira.echsan.features.bookings.data.datasource.BookingsRemoteDataSource
import com.vira.echsan.features.umroh.data.datasource.PaketUmrohRemoteDataSource
import com.vira.echsan.features.umroh.data.repo.PaketUmrohRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookingsRepository @Inject constructor(
    private val dataSource: BookingsRemoteDataSource
) {

    fun observePagedSets(userId: Int? = null, coroutineScope: CoroutineScope) =
        observeRemotePagedSets(userId, coroutineScope)

    private fun observeRemotePagedSets(
        userId: Int? = null,
        ioCoroutineScope: CoroutineScope
    ): LiveData<PagedList<TransactionResp>> {
        val dataSourceFactory = BookingsPageDataSourceFactory(
            userId,
            dataSource, ioCoroutineScope
        )
        return LivePagedListBuilder(
            dataSourceFactory,
            BookingsPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    suspend fun getTransaction(code: String) = dataSource.fetchBooking(code)

    companion object {
        @Volatile
        private var instance: PaketUmrohRepository? = null

        fun getInstance(
            dao: PaketUmrohDao,
            paketUmrohRemoteDataSource: PaketUmrohRemoteDataSource
        ) =
            instance ?: synchronized(this) {
                instance ?: PaketUmrohRepository(dao, paketUmrohRemoteDataSource).also {
                    instance = it
                }
            }
    }
}