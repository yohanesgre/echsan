package com.vira.echsan.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vira.echsan.data.entities.Booking

@Dao
interface BookingDao{
    @Query("SELECT * FROM bookings ORDER BY id")
    fun getBookings(): LiveData<List<Booking>>

    @Query("SELECT * FROM bookings where id = :id")
    fun getBooking(id:Int): LiveData<Booking>

    @Query("SELECT * FROM bookings where bookingId = :id")
    fun getBookingById(id:Int): LiveData<Booking>

    @Query("SELECT * FROM bookings where status = :status")
    fun getBookingsByStatus(status:Int): LiveData<List<Booking>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(bookings:List<Booking>)
}