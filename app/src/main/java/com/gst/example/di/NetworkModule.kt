package com.gst.example.di

import com.gst.example.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import com.gst.example.NetworkConfig
import com.gst.example.data.datasources.remote.api.DynamicApiService
import com.gst.example.data.datasources.remote.api.StringConverterFactory
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .callTimeout(NetworkConfig.CALL_TIMEOUT, TimeUnit.MILLISECONDS)
      .connectTimeout(NetworkConfig.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
      .readTimeout(NetworkConfig.READ_TIMEOUT, TimeUnit.MILLISECONDS)
      .writeTimeout(NetworkConfig.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
      .apply {
        if (BuildConfig.DEBUG) {
          val loggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
          addInterceptor(loggingInterceptor)
        }
      }
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
    val responseContentType = "application/json".toMediaType()
    return Retrofit.Builder()
      .addConverterFactory(StringConverterFactory())
      .addConverterFactory(Json.asConverterFactory(responseContentType))
      .client(okHttpClient)
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
  }

  @Provides
  @Singleton
  fun provideDynamicApiService(retrofitBuilder: Retrofit.Builder): DynamicApiService {
    return DynamicApiService(retrofitBuilder)
  }
}