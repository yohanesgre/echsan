package com.vira.echsan.data.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun getProfile(): LiveData<Profile>

    @Query("SELECT * FROM profile WHERE id = :id")
    fun getProfileId(id: Int): LiveData<Profile>

    @Query("SELECT COUNT (*) FROM profile")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile)
}