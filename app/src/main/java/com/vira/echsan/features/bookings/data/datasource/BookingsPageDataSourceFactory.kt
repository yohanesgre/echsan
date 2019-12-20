package com.vira.echsan.features.bookings.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.vira.echsan.api.resp.TransactionResp
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class BookingsPageDataSourceFactory @Inject constructor(
    private val userId: Int? = null,
    private val dataSource: BookingsRemoteDataSource,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, TransactionResp>() {

    private val liveData = MutableLiveData<BookingsPageDataSource>()

    override fun create(): DataSource<Int, TransactionResp> {
        val source = BookingsPageDataSource(userId, dataSource, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 10

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
    }

}