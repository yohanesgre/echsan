package com.vira.echsan.di


import com.vira.echsan.features.profile.view.ProfileDetilFragment
import com.vira.echsan.features.profile.view.ProfileEditFragment
import com.vira.echsan.features.profile.view.ProfileUbahPasswordFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ProfileFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeProfileEditFragment(): ProfileEditFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileDetilFragment(): ProfileDetilFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileUbahPasswordFragment(): ProfileUbahPasswordFragment
}
