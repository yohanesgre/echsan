package com.vira.echsan.features.umroh.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vira.echsan.data.dao.PaketUmrohDao
import com.vira.echsan.data.resultLiveData
import com.vira.echsan.features.umroh.data.PaketUmroh
import com.vira.echsan.features.umroh.data.datasource.PaketUmrohPageDataSourceFactory
import com.vira.echsan.features.umroh.data.datasource.PaketUmrohRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaketUmrohRepository @Inject constructor(
    private val dao: PaketUmrohDao,
    private val paketUmrohRemoteDataSource: PaketUmrohRemoteDataSource
) {

    fun observePagedSets(coroutineScope: CoroutineScope) =
        observeRemotePagedSets(coroutineScope)

    private fun observeRemotePagedSets(ioCoroutineScope: CoroutineScope): LiveData<PagedList<PaketUmroh>> {
        val dataSourceFactory = PaketUmrohPageDataSourceFactory(
            paketUmrohRemoteDataSource,
            dao, ioCoroutineScope
        )
        return LivePagedListBuilder(
            dataSourceFactory,
            PaketUmrohPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    suspend fun getPaketUmroh(id: Int) = paketUmrohRemoteDataSource.fetchPaketUmroh(id)

    fun observePaketUmroh(id: Int) = resultLiveData(
        databaseQuery = { dao.getPaketUmroh(id) },
        networkCall = { paketUmrohRemoteDataSource.fetchPaketUmroh(id) },
        saveCallResult = { })
        .distinctUntilChanged()

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