package com.vira.echsan.data.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vira.echsan.data.resultLiveData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaketUmrohRepository @Inject constructor(
    private val dao: PaketUmrohDao,
    private val paketUmrohRemoteDataSource: PaketUmrohRemoteDataSource
) {

    fun observePagedSets(connectivityAvailable: Boolean, coroutineScope: CoroutineScope) =
        if (connectivityAvailable) observeRemotePagedSets(coroutineScope)
        else observeLocalPagedSets()

    private fun observeLocalPagedSets(): LiveData<PagedList<PaketUmroh>> {
        val dataSourceFactory = dao.getPagedPaketUmrohs()
        return LivePagedListBuilder(
            dataSourceFactory,
            PaketUmrohPageDataSourceFactory.pagedListConfig()
        ).build()
    }

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

    fun observePaketUmroh(id: Int) = resultLiveData(
        databaseQuery = { dao.getPaketUmroh(id) },
        networkCall = { paketUmrohRemoteDataSource.fetchPaketUmroh(id) },
        saveCallResult = { })
        .distinctUntilChanged()

    companion object{
        @Volatile private var instance: PaketUmrohRepository? = null
        fun getInstance(
            dao: PaketUmrohDao,
            paketUmrohRemoteDataSource: PaketUmrohRemoteDataSource
        ) =
            instance?: synchronized(this){
                instance ?: PaketUmrohRepository(dao, paketUmrohRemoteDataSource).also {
                    instance = it
                }
            }
    }
}