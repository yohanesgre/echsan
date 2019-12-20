package com.vira.echsan.data.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.vira.echsan.features.umroh.data.PaketUmroh


@Dao
interface PaketUmrohDao {
    @Query("SELECT * FROM PaketUmroh ORDER BY id ASC")
    fun getPaketUmrohs(): LiveData<List<PaketUmroh>>

    @Query("SELECT * FROM PaketUmroh ORDER BY id ASC")
    fun getPagedPaketUmrohs(): DataSource.Factory<Int, PaketUmroh>

    @Query("SELECT * FROM PaketUmroh WHERE id = :id")
    fun getPaketUmroh(id: Int): LiveData<PaketUmroh>

    @Query("DELETE FROM PaketUmroh")
    suspend fun deleteAll()

    /* @Query("DELETE FROM Category")
     suspend fun deleteAllCategory()
 */
    @Transaction
    suspend fun deleteAllAndInsert(paket: List<PaketUmroh>) {
        deleteAll()
        //      deleteAllCategory()
        paket.forEach {
            insert(it)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(paketUmroh: PaketUmroh)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(paketUmrohs: List<PaketUmroh>)
}