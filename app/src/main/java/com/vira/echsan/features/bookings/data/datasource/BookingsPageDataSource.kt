package com.vira.echsan.features.bookings.data.datasource

import androidx.paging.PageKeyedDataSource
import com.google.gson.Gson
import com.vira.echsan.api.ResultsResponse
import com.vira.echsan.api.resp.TransactionResp
import com.vira.echsan.data.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Data source for lego sets pagination via paging library
 */
class BookingsPageDataSource @Inject constructor(
    private val isDone: Boolean? = null,
    private val userId: Int? = null,
    private val dataSource: BookingsRemoteDataSource,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, TransactionResp>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TransactionResp>
    ) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, null)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TransactionResp>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TransactionResp>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<TransactionResp>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response: Result<ResultsResponse<TransactionResp>> =
                if (isDone!!)
                    dataSource.fetchBookingsDone(userId!!, page, pageSize)
                else
                    dataSource.fetchBookingsUndone(userId!!, page, pageSize)

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