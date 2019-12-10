package com.vira.echsan.data.entities

import androidx.paging.PageKeyedDataSource
import com.vira.echsan.data.Result
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

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PaketUmroh>
    ) {
        fetchData {
            callback.onResult(it, null, null)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PaketUmroh>) {
        val page = params.key
        fetchData {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PaketUmroh>) {
        val page = params.key
        fetchData {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(callback: (List<PaketUmroh>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.fetchPaketUmrohs()
            if (response.status == Result.Status.SUCCESS) {
                val results = response.data!!.data
                //println("Result Data: ${response.data!!.data}")
                //dao.insertAll(results)
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