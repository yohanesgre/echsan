package com.vira.echsan.data.entities

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromoRepository @Inject constructor(private val promoDao: PromoDao){
    fun getPromos() = promoDao.getPromos()
    fun getPromo(promoId:Int) = promoDao.getPromo(promoId)

    companion object{
        @Volatile private var instance: PromoRepository? = null
        fun getInstance(promoDao: PromoDao) =
            instance?: synchronized(this){
                instance?: PromoRepository(promoDao).also{
                    instance = it
                }
            }
    }
}