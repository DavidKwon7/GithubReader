package com.example.app.di

import com.example.data.repository.RemoteDataSource
import com.example.data.repository.RepositoryImpl
import com.example.domain.repository.Repository
import com.example.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun provideRepository(repositoryImpl: RepositoryImpl): Repository
}