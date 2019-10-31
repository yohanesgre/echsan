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

    @Query("SELECT * FROM paket_umrohs WHERE tanggal = :tanggal AND lokasi = :lokasi AND tipe = :tipe")
    fun searchPakets(tipe:String, lokasi:String, tanggal:String): LiveData<List<PaketUmroh>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(paketUmrohs: List<PaketUmroh>)
}