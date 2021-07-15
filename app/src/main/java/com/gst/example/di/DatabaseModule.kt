package com.gst.example.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import com.gst.example.data.datasources.local.ISecureStorageManager
import com.gst.example.data.datasources.local.SecureStorageManager
import com.gst.example.data.datasources.memory.IMemoryDataStorage
import com.gst.example.data.datasources.memory.MemoryDataStorage
import com.gst.example.data.datasources.remote.AuthenticationRemoteDataSource
import com.gst.example.data.datasources.remote.IAuthenticationRemoteDataSource
import com.gst.example.data.datasources.remote.api.DynamicApiService
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
  @Provides
  @Singleton
  fun provideMemoryStorage(): IMemoryDataStorage = MemoryDataStorage()

  @Provides
  @Singleton
  fun providerSecureStorageManager(@ApplicationContext context: Context): ISecureStorageManager =
    SecureStorageManager(context)

  @Provides
  @Singleton
  fun providerAuthenticationRemoteDataSource(dynamicApiService: DynamicApiService): IAuthenticationRemoteDataSource =
    AuthenticationRemoteDataSource(dynamicApiService)
}