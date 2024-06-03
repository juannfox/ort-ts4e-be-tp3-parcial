package com.example.parcial.di

import android.content.Context
import androidx.room.Room
import com.example.parcial.data.database.TripDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val TRIP_DATABASE_NAME = "trip_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TripDatabase::class.java, TRIP_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideFavouriteDao(db: TripDatabase) = db.getFavouriteDao()

}