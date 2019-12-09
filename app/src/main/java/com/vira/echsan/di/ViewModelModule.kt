package com.vira.echsan.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vira.echsan.viewmodel.*

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
    @ViewModelKey(PaketUmrohViewModel::class)
    abstract fun bindPaketUmrohViewModel(viewModel: PaketUmrohViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaketUmrohsViewModel::class)
    abstract fun bindPaketUmrohsViewModel(viewModel: PaketUmrohsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaketPemesananViewModel::class)
    abstract fun bindPaketPemesananViewModel(viewModel: PaketPemesananViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaketPembayaranViewModel::class)
    abstract fun bindPaketPembayaranViewModel(viewModel: PaketPembayaranViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaketPembayaran2ViewModel::class)
    abstract fun bindPaketPembayaran2ViewModel(viewModel: PaketPembayaran2ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaketPembayaran3ViewModel::class)
    abstract fun bindPaketPembayaran3ViewModel(viewModel: PaketPembayaran3ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaketBerhasilViewModel::class)
    abstract fun bindPaketBerhasilViewModel(viewModel: PaketBerhasilViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
