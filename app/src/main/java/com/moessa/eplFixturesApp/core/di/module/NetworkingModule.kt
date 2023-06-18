package com.moessa.eplFixturesApp.core.di.module

import android.content.Context
import com.moessa.eplFixturesApp.BuildConfig
import com.moessa.eplFixturesApp.BuildConfig.API_Token
import com.moessa.eplFixturesApp.BuildConfig.API_URL
import com.moessa.eplFixturesApp.core.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext appContext: Context) = NetworkHelper(appContext)

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =

        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(Interceptor {
                    val request = it.request()
                    val newRequest = request.newBuilder().header("X-Auth-Token", API_Token)
                    return@Interceptor it.proceed(newRequest.build())
                })
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(Interceptor {
                    val newRequest = it.request().newBuilder().header("X-Auth-Token", API_Token)
                    return@Interceptor it.proceed(newRequest.build())
                })
                .build()
        }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}