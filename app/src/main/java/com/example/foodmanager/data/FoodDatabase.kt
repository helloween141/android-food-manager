package com.example.foodmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodmanager.data.dao.FoodDao
import com.example.foodmanager.data.entity.Food

@Database(
    entities = [Food::class],
    version = 2,
    exportSchema = false
)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun getFoodDao(): FoodDao
}