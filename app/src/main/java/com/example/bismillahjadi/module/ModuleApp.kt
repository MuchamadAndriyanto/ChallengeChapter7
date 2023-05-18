package com.example.bismillahjadi.module

import android.app.Application
import androidx.room.Room
import com.example.bismillahjadi.room.FavoritDao
import com.example.bismillahjadi.room.FavoritDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleApp {
    @Singleton
    @Provides
    fun provideDatabase(app: Application):FavoritDatabase {
        return Room.databaseBuilder(app, FavoritDatabase::class.java, "favorite_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(database:FavoritDatabase):FavoritDao {
        return database.favoritDao()
    }
}