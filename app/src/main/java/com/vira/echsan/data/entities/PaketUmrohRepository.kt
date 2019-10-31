package com.vira.echsan.data.entities

import javax.inject.Inject

class PaketUmrohRepository @Inject constructor(private val paketUmrohDao: PaketUmrohDao){

    fun getPakets() = paketUmrohDao.getPaketUmrohs()
    fun searchPakets(tipe:String, lokasi:String, tanggal:String) = paketUmrohDao.searchPakets(tipe, lokasi, tanggal)
    fun getPaket(paketId:Int) = paketUmrohDao.getPaketUmroh(paketId)

    companion object{
        @Volatile private var instance: PaketUmrohRepository? = null
        fun getInstance(dao: PaketUmrohDao) =
            instance?: synchronized(this){
                instance?: PaketUmrohRepository(dao).also{
                    instance = it
                }
            }
    }
}