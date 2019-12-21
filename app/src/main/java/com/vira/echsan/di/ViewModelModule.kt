package com.vira.echsan.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vira.echsan.features.bookings.viewmodel.BookingsChildViewModel
import com.vira.echsan.features.home.viewmodel.HomeViewModel
import com.vira.echsan.features.login.viewmodel.LoginViewModel
import com.vira.echsan.features.profile.viewmodel.ProfileDetilViewModel
import com.vira.echsan.features.profile.viewmodel.ProfileEditViewModel
import com.vira.echsan.features.profile.viewmodel.ProfileUbahPasswordViewModel
import com.vira.echsan.features.regis.viewmodel.RegisterViewModel
import com.vira.echsan.features.umroh.viewmodel.*
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
    @ViewModelKey(BookingsChildViewModel::class)
    abstract fun bindBookingsChildViewModel(viewModel: BookingsChildViewModel): ViewModel

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
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileDetilViewModel::class)
    abstract fun bindProfileDetilViewModel(viewModel: ProfileDetilViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileEditViewModel::class)
    abstract fun bindProfileEditViewModel(viewModel: ProfileEditViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileUbahPasswordViewModel::class)
    abstract fun bindProfileUbahPasswordViewModel(viewModel: ProfileUbahPasswordViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
