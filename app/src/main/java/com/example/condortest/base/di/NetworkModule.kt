package com.example.condortest.base.di

import com.example.condortest.base.di.utilities.ApiTheSports
import com.example.condortest.base.di.utilities.Constants.Companion.BASE_URL
import com.example.condortest.data.datasources.TheSportsRemoteDataSourceImp
import com.example.condortest.data.datasources.interfaces.TheSportsRemoteDataSource
import com.example.condortest.data.repositories.TheSportsRepositoryImp
import com.example.condortest.data.repositories.interfaces.TheSportsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): ApiTheSports = retrofit.create(ApiTheSports::class.java)

}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindTheSportsRepositoryImp(
        theSportsRepositoryImp: TheSportsRepositoryImp,
    ): TheSportsRepository

    @Binds
    @Singleton
    abstract fun bindTheSportsRemoteDataSourceImp(
        theSportsRemoteDataSourceImp: TheSportsRemoteDataSourceImp,
    ): TheSportsRemoteDataSource
}