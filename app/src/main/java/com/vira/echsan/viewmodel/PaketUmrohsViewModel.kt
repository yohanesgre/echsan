package com.vira.echsan.viewmodel

import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.PaketUmrohRepository
import com.vira.echsan.di.CoroutineScropeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class PaketUmrohsViewModel @Inject constructor(
    private val repository: PaketUmrohRepository,
    @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {

    var connectivityAvailable: Boolean = true

    val paketUmrohs by lazy {
        repository.observePagedSets(
            connectivityAvailable, ioCoroutineScope
        )
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }

    /*
    var tipeUmroh = arrayOf("Umroh Reguler", "Umroh VIP")
    var kotaUmroh = arrayOf("Surabaya", "Jakarta", "Malang")
    var tglUmroh = arrayOf("Oktober 2019", "November 2019")
    var jamaahUmroh = arrayOf(1,2,3,4,5)
    */
}