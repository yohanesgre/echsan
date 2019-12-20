package com.vira.echsan.di

import com.vira.echsan.LoginActivity
import com.vira.echsan.MainActivity
import com.vira.echsan.SplashActivity
import com.vira.echsan.features.MemberActivity
import com.vira.echsan.features.social.SocialActivity
import com.vira.echsan.features.umroh.UmrohActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [UmrohFragmentBuildersModule::class])
    abstract fun contributeUmrohActivity(): UmrohActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMemberActivity(): MemberActivity

    @ContributesAndroidInjector(modules = [SocialFragmentBuildersModule::class])
    abstract fun contributeSocialActivity(): SocialActivity
}
