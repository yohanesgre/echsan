package com.vira.echsan.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module to provide core data functionality.
 */
@Module
class CoreDataModule {
/*
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().addInterceptor(interceptor)
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()

    @Provides
    fun provideLoggingInterceptor() =
            HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
            GsonConverterFactory.create(gson)

 */
}
