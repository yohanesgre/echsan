package com.vira.echsan.di


import com.vira.echsan.features.bookings.view.BookingsChildFragment
import com.vira.echsan.features.bookings.view.BookingsFragment
import com.vira.echsan.features.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeBookingsFragment(): BookingsFragment

    @ContributesAndroidInjector
    abstract fun contributeBookingsChildFragment(): BookingsChildFragment
}
