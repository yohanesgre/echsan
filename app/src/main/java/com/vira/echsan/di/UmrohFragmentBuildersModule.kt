package com.vira.echsan.di


import com.vira.echsan.ui.fragments.umroh.UmrohCariFragment
import com.vira.echsan.ui.fragments.umroh.UmrohHasilFragment
import com.vira.echsan.ui.fragments.umroh.UmrohPaketFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class UmrohFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeUmrohCariFragment(): UmrohCariFragment

    @ContributesAndroidInjector
    abstract fun contributeUmrohHasilFragment(): UmrohHasilFragment

    @ContributesAndroidInjector
    abstract fun contributeUmrohPaketFragment(): UmrohPaketFragment
}
