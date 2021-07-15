package com.gst.example.di

import com.gst.example.data.datasources.remote.AuthenticationRemoteDataSource
import com.gst.example.data.datasources.remote.IAuthenticationRemoteDataSource
import com.gst.example.data.datasources.remote.api.DynamicApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataSourcesModule {
    @Provides
    fun providerAuthenticationRemoteDataSource(
        apiService: DynamicApiService
    ) = AuthenticationRemoteDataSource(apiService)
}