package com.vira.echsan.data.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PaketUmrohDao {
    @Query("SELECT * FROM paket_umrohs ORDER BY id")
    fun getPaketUmrohs():LiveData<List<PaketUmroh>>

    @Query("SELECT * FROM paket_umrohs WHERE id = :paketId")
    fun getPaketUmroh(paketId:Int): LiveData<PaketUmroh>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(paketUmrohs: List<PaketUmroh>)
}