package com.taeyang.autocrypt.di

import android.content.Context
import androidx.room.Room
import com.taeyang.autocrypt.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(
        @ApplicationContext app: Context
    )= Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "centers"
    ).build()

    @Singleton
    @Provides
    fun provideCentersDao(database: AppDatabase) = database.centersDao()

}