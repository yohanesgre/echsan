package com.vira.echsan.features.umroh.data.datasource

import androidx.paging.PageKeyedDataSource
import com.google.gson.Gson
import com.vira.echsan.data.Result
import com.vira.echsan.data.dao.PaketUmrohDao
import com.vira.echsan.features.umroh.data.PaketUmroh
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Data source for lego sets pagination via paging library
 */
class PaketUmrohPageDataSource @Inject constructor(
    private val dataSource: PaketUmrohRemoteDataSource,
    private val dao: PaketUmrohDao,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, PaketUmroh>() {

    var nextPage: Int = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PaketUmroh>
    ) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, null)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PaketUmroh>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PaketUmroh>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<PaketUmroh>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.fetchPaketUmrohs(page, pageSize)
            if (response.status == Result.Status.SUCCESS) {
                val results = response.data!!.data
                val gson = Gson()
                println("Result Json: ${gson.toJson(results)}")
                println("Going to callback")
                callback(results)
            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        //Timber.e("An error happened: $message")
        // TODO network error handling
        //networkState.postValue(NetworkState.FAILED)
    }

}