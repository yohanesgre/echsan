package com.vira.echsan

import android.app.Activity
import android.app.Application
import android.content.Context
import com.vira.echsan.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
            AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}