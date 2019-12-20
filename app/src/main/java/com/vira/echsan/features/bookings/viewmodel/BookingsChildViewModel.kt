package com.vira.echsan.features.bookings.viewmodel

import androidx.lifecycle.*
import com.vira.echsan.api.resp.TransactionResp
import com.vira.echsan.di.CoroutineScropeIO
import com.vira.echsan.features.bookings.data.repo.BookingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class BookingsChildViewModel @Inject constructor(
    repository: BookingsRepository,
    @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel(){
    var position = MutableLiveData<Int>()
    var userId: Int? = null

    val bookings by lazy {
        repository.observePagedSets(
            userId, ioCoroutineScope
        )
    }

    val transformedBookings: LiveData<List<TransactionResp>>
        get() = Transformations.switchMap(position) { pos ->
            val allBooksList = bookings.value?.toList()
            val allBooksLD = liveData {
                emit(allBooksList!!)
            }
            val books = when {
                pos == null -> allBooksLD
                else -> {
                    Transformations.switchMap(allBooksLD) { trxList ->
                        val filteredPlayers = MutableLiveData<List<TransactionResp>>()
                        val filteredList = trxList.filter { trx -> trx.paidDone == pos }
                        filteredPlayers.value = filteredList
                        filteredPlayers
                    }
                }
            }
            books
        }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}