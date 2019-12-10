package com.vira.echsan.data.entities

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PaketUmrohPageDataSourceFactory @Inject constructor(
    private val dataSource: PaketUmrohRemoteDataSource,
    private val dao: PaketUmrohDao,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, PaketUmroh>() {

    private val liveData = MutableLiveData<PaketUmrohPageDataSource>()

    override fun create(): DataSource<Int, PaketUmroh> {
        val source = PaketUmrohPageDataSource(dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 10

        fun pagedListConfig() = PagedList.Config.Builder()
            //.setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
    }

}