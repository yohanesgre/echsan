package com.vira.echsan.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vira.echsan.data.entities.Booking
import com.vira.echsan.data.entities.BookingRepository

class BookingsChildViewModel internal constructor(
    bookingRepository: BookingRepository,
    private val status:Int
) : ViewModel(){
    val bookingByStatus: LiveData<List<Booking>> =  bookingRepository.getBookingsByStatus(status)
}