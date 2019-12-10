package com.vira.echsan.data.entities

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PaketUmrohDao {
    @Query("SELECT * FROM paket_umrohs ORDER BY id ASC")
    fun getPaketUmrohs(): LiveData<List<PaketUmroh>>

    @Query("SELECT * FROM paket_umrohs ORDER BY id ASC")
    fun getPagedPaketUmrohs(): DataSource.Factory<Int, PaketUmroh>

    @Query("SELECT * FROM paket_umrohs WHERE id = :id")
    fun getPaketUmroh(id: Int): LiveData<PaketUmroh>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(paketUmrohs: List<PaketUmroh>)
}