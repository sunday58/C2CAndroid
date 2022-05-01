package com.app.c2candroid.di

import com.app.c2candroid.apiResponse.ExhibitLoader
import com.app.c2candroid.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson{
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson): Retrofit.Builder{

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .build()
            val response = chain.proceed(newRequest)
            response
        }).addInterceptor(httpLoggingInterceptor)
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .build()


        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun providesBlogService(retrofit: Retrofit.Builder): ExhibitLoader{
        return retrofit
            .build()
            .create(ExhibitLoader::class.java)
    }
}