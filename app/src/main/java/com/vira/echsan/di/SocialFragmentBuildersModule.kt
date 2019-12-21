package com.vira.echsan.di

import com.vira.echsan.features.social.view.SocialDonateFragment
import com.vira.echsan.features.social.view.SocialFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class SocialFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSocialFragment(): SocialFragment

    @ContributesAndroidInjector
    abstract fun contributeSocialDonateFragment(): SocialDonateFragment
}
