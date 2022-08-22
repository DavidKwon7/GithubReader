package com.example.githubreader.di

import android.content.Context
import androidx.room.Room
import com.example.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "database")
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDAO(appDatabase: AppDatabase) = appDatabase.favDao()
}