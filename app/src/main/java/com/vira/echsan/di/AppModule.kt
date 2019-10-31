package com.vira.echsan.di

import android.app.Application
import com.vira.echsan.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {
/*
    @Singleton
    @Provides
    fun provideLegoService(@LegoAPI okhttpClient: OkHttpClient,
                           converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, LegoService::class.java)
*/
  /*  @Singleton
    @Provides
    fun provideLegoSetRemoteDataSource(legoService: LegoService)
            = LegoSetRemoteDataSource(legoService)
*/
  /*  @Singleton
    @Provides
    fun provideLegoThemeRemoteDataSource(legoService: LegoService)
            = LegoThemeRemoteDataSource(legoService)
*/
  /*  @LegoAPI
    @Provides
    fun providePrivateOkHttpClient(
            upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
                .addInterceptor(AuthInterceptor(BuildConfig.API_DEVELOPER_TOKEN)).build()
    }
*/
    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun providePaketUmrohDao(db: AppDatabase) = db.paketUmrohDao()


    @Singleton
    @Provides
    fun providePromoDao(db: AppDatabase) = db.promoDao()

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

/*
    private fun createRetrofit(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(LegoService.ENDPOINT)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }*/
}
