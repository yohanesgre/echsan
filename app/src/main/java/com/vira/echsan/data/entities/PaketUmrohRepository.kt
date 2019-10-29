package com.vira.echsan.data.entities

class PaketUmrohRepository private constructor(private val paketUmrohDao: PaketUmrohDao){

    fun getPakets() = paketUmrohDao.getPaketUmrohs()

    fun getPaket(paketId:Int) = paketUmrohDao.getPaketUmroh(paketId)

    companion object{
        @Volatile private var instance: PaketUmrohRepository? = null
        fun getInstance(paketUmrohDao: PaketUmrohDao) =
            instance?: synchronized(this){
                instance?: PaketUmrohRepository(paketUmrohDao).also{
                    instance = it
                }
            }
    }
}