package com.vira.echsan.data.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PromoDao {
    @Query("SELECT * FROM promos ORDER BY id")
    fun getPromos():LiveData<List<Promo>>

    @Query("SELECT * FROM promos where id = :promoId")
    fun getPromo(promoId:Int):LiveData<Promo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(promos:List<Promo>)
}