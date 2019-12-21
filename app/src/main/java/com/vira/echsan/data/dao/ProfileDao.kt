package com.vira.echsan.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vira.echsan.data.entities.Profile

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun getProfile(): LiveData<Profile>

    @Query("SELECT * FROM profile WHERE id = :id")
    fun getProfileId(id: Int): LiveData<Profile>

    @Query("SELECT COUNT (*) FROM profile")
    fun getCount(): Int

    @Query("DELETE FROM profile")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile)
}