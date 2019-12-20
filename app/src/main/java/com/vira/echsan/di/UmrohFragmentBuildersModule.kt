package com.vira.echsan.di


import com.vira.echsan.features.umroh.view.*
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

    @ContributesAndroidInjector
    abstract fun contributeUmrohPaketPemesananFragment(): UmrohPaketPemesananFragment

    @ContributesAndroidInjector
    abstract fun contributeUmrohPaketPembayaranFragment(): UmrohPaketPembayaranFragment

    @ContributesAndroidInjector
    abstract fun contributeUmrohPaketPembayaran2Fragment(): UmrohPaketPembayaran2Fragment

    @ContributesAndroidInjector
    abstract fun contributeUmrohPaketPembayaran3Fragment(): UmrohPaketPembayaran3Fragment

    @ContributesAndroidInjector
    abstract fun contributeUmrohPaketBerhasilFragment(): UmrohPaketBerhasilFragment

    @ContributesAndroidInjector
    abstract fun contributeUmrohPaketInputJamaahFragment(): UmrohPaketInputJamaahFragment
}
