package com.vira.echsan.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vira.echsan.viewmodel.HomeViewModel
import com.vira.echsan.viewmodel.LoginViewModel
import com.vira.echsan.viewmodel.PaketUmrohViewModel
import com.vira.echsan.viewmodel.PaketUmrohsViewModel

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
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
