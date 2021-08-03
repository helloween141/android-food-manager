package com.example.foodmanager.data.dao

import androidx.room.*
import com.example.foodmanager.data.entity.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getAll(): Flow<List<Food>>

    @Query("SELECT * FROM food WHERE id = :id")
    fun getOneById(id: Long): Flow<Food>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(food: Food)

    @Update
    suspend fun update(food: Food)

    @Delete
    suspend fun delete(food: Food)
}