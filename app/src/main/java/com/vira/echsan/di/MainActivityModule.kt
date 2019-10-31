package com.vira.echsan.di

import com.vira.echsan.MainActivity
import com.vira.echsan.ui.activities.umroh_haji.UmrohActivity
import com.vira.echsan.ui.fragments.umroh.UmrohPaketFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeUmrohActivity(): UmrohActivity
}
