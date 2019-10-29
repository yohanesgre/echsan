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

package com.vira.echsan.utils

import android.content.Context
import com.vira.echsan.data.database.AppDatabase
import com.vira.echsan.data.entities.BookingRepository
import com.vira.echsan.data.entities.PromoRepository
import com.vira.echsan.ui.viewmodel.BookingsChildViewModelFactory
import com.vira.echsan.ui.viewmodel.HomeViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getPromoRepository(context: Context): PromoRepository {
        return PromoRepository.getInstance(
                AppDatabase.getInstance(context.applicationContext).promoDao())
    }

    private fun getBookingRepository(context: Context): BookingRepository {
        return BookingRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).bookingDao())
    }

    fun provideHomeViewModelFactory(context: Context): HomeViewModelFactory {
        return HomeViewModelFactory(getPromoRepository(context))
    }

    fun provideBookingsChieldViewModelFactory(
        context: Context,
        status:Int
        ): BookingsChildViewModelFactory {
        return BookingsChildViewModelFactory(getBookingRepository(context), status)
    }
/*
    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(
                AppDatabase.getInstance(context.applicationContext).gardenPlantingDao())
    }

    fun provideGardenPlantingListViewModelFactory(
        context: Context
    ): GardenPlantingListViewModelFactory {
        val repository = getGardenPlantingRepository(context)
        return GardenPlantingListViewModelFactory(repository)
    }

    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }

    fun providePlantDetailViewModelFactory(
        context: Context,
        plantId: String
    ): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(getPlantRepository(context),
                getGardenPlantingRepository(context), plantId)
    }*/
}
