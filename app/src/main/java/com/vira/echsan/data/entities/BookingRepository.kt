package com.vira.echsan.data.entities

import javax.inject.Singleton

@Singleton
class BookingRepository private constructor(private val bookingDao: BookingDao){
    fun getBookings() = bookingDao.getBookings()
    fun getBookingsByStatus(status:Int) = bookingDao.getBookingsByStatus(status)

    companion object{
        @Volatile private var instance: BookingRepository? = null
        fun getInstance(bookingDao: BookingDao) =
            instance?: synchronized(this){
                instance?: BookingRepository(bookingDao).also{
                    instance = it
                }
            }
    }
}