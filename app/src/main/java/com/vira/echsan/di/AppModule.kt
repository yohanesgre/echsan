package com.vira.echsan.di

import android.app.Application
import com.vira.echsan.api.LoginService
import com.vira.echsan.api.UmrohService
import com.vira.echsan.data.database.AppDatabase
import com.vira.echsan.data.entities.PaketUmrohRemoteDataSource
import com.vira.echsan.data.entities.ProfileRemoteDataSource
import com.vira.echsan.utils.ENDPOINT
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideLoginService(
        @NonAuthAPI okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, LoginService::class.java)

    @Singleton
    @Provides
    fun provideUmrohService(
        @NonAuthAPI okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, UmrohService::class.java)

    @Singleton
    @Provides
    fun provideProfileRemoteDataSource(loginService: LoginService) =
        ProfileRemoteDataSource(loginService)

    @Singleton
    @Provides
    fun providePaketUmrohRemoteDataSource(umrohService: UmrohService) =
        PaketUmrohRemoteDataSource(umrohService)

    /*
    @Singleton
    @Provides
    fun provideLegoThemeRemoteDataSource(legoService: LegoService)
            = LegoThemeRemoteDataSource(legoService)
    */
    @NonAuthAPI
    @Provides
    fun provideLoginOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder().build()
    }

    /*
        @UmrohAPI
        @Provides
        fun provideUmrohOkHttpClient(
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

    @Singleton
    @Provides
    fun provideProfileDao(db: AppDatabase) = db.profileDao()

    /*
        @Singleton
        @Provides
        fun provideCategoryDao(db: AppDatabase) = db.categoryDao()

        @Singleton
        @Provides
        fun provideDepartureCityDao(db: AppDatabase) = db.departureCityDao()

        @Singleton
        @Provides
        fun provideDeparturePlaneDao(db: AppDatabase) = db.departurePlaneDao()

        @Singleton
        @Provides
        fun provideProductCategoryDao(db: AppDatabase) = db.productCategoryDao()

        @Singleton
        @Provides
        fun provideReturnPlaneDao(db: AppDatabase) = db.returnPlaneDao()

        @Singleton
        @Provides
        fun provideTravelVendorDao(db: AppDatabase) = db.travelVendorDao()
    */
    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)


    private fun createRetrofit(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ENDPOINT)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
