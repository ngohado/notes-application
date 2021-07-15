package com.gst.example.di

import com.gst.example.data.datasources.local.ISecureStorageManager
import com.gst.example.data.datasources.memory.IMemoryDataStorage
import com.gst.example.data.datasources.remote.IAuthenticationRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import com.gst.example.data.repositories.authentication.AuthenticationRepository
import com.gst.example.data.repositories.authentication.AuthenticationRepositoryImpl
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {
    @Provides
    fun provideAuthenticationRepository(
        authenticationRemoteDataSource: IAuthenticationRemoteDataSource,
        secureStorageManager: ISecureStorageManager,
        memoryDataStorage: IMemoryDataStorage
    ): AuthenticationRepository = AuthenticationRepositoryImpl(
        authenticationRemoteDataSource,
        secureStorageManager,
        memoryDataStorage
    )
}