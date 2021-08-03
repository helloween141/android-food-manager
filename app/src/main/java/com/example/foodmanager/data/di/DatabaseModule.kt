package com.example.foodmanager.data.di

import android.content.Context
import androidx.room.Room
import com.example.foodmanager.data.FoodDatabase
import com.example.foodmanager.data.dao.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideFoodDao(foodDatabase: FoodDatabase): FoodDao {
        return foodDatabase.getFoodDao()
    }

    @Provides
    @Singleton
    fun provideFoodDatabase(@ApplicationContext appContext: Context): FoodDatabase {
        return Room.databaseBuilder(
            appContext,
            FoodDatabase::class.java,
            "FoodDB"
        ).fallbackToDestructiveMigration().build()
    }
}