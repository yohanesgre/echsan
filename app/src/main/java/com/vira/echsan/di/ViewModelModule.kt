package com.vira.echsan.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vira.echsan.viewmodel.HomeViewModel
import com.vira.echsan.viewmodel.UmrohViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UmrohViewModel::class)
    abstract fun bindUmrohViewModel(viewModel: UmrohViewModel): ViewModel
/*
    @Binds
    @IntoMap
    @ViewModelKey(LegoSetViewModel::class)
    abstract fun bindLegoSetViewModel(viewModel: LegoSetViewModel): ViewModel
*/
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
