/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vira.echsan.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.vira.echsan.data.entities.*
import com.vira.echsan.utils.DATABASE_NAME
import com.vira.echsan.utils.ioThread
import com.vira.echsan.workers.SeedDatabaseWorker

/**
 * The Room database for this app
 */
@Database(entities = [PaketUmroh::class, Promo::class, Booking::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun paketUmrohDao(): PaketUmrohDao
    abstract fun promoDao(): PromoDao
    abstract fun bookingDao(): BookingDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        /*
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(seedDatabaseCallback(context))
                .build()*/
/*
        private fun seedDatabaseCallback(context: Context):Callback{
            return object: Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    ioThread {
                        prepopulatePromo(context)
                        prepopulatePakets(context)
                    }
                }
            }
        }

        private fun prepopulatePromo(context: Context){
            val promoDao = getInstance(context).promoDao()

            val promos = mutableListOf<Promo>().apply {
                this.add(Promo(1, "Promo1"))
                this.add(Promo(2, "Promo1"))
            }
            promoDao.insertAll(promos.toList())
        }

        private suspend fun prepopulatePakets(context: Context){
            val paketUmrohDao = getInstance(context)!!.paketUmrohDao()
            val pakets = mutableListOf<PaketUmroh>().apply {
                this.add(
                    PaketUmroh(1, "Umroh", "Reguler", "Lintas Darfiq", "Garuda",
                        "Oktober 2019", "10 Hari", 25000000.0, "Hotel", 100, 20, "Surabaya"))
                this.add(
                    PaketUmroh(2, "Umroh", "Reguler", "Lintas Darfiq", "Garuda",
                        "Oktober 2019", "12 Hari", 30000000.0, "Hotel", 100, 20, "Malang"))
                this.add(
                    PaketUmroh(3, "Umroh", "Reguler", "Lintas Darfiq", "Garuda",
                        "Oktober 2019", "15 Hari", 35000000.0, "Hotel", 100, 20, "Jakarta"))
            }
            paketUmrohDao.insertAll(pakets)
        }

 */
        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785/

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    })
                    .build()
        }
    }
}
