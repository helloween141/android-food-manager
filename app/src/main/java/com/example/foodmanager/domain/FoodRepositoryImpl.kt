package com.example.foodmanager.domain

import com.example.foodmanager.data.dao.FoodDao
import com.example.foodmanager.data.entity.Food
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FoodRepositoryImpl @Inject constructor(private val foodDao: FoodDao) {

    fun getAll(): Flow<List<Food>> {
        return foodDao.getAll()
    }

    fun getById(id: Int): Flow<Food> {
        return foodDao.getOneById(id)
    }

    suspend fun create(food: Food) {
        return foodDao.insert(food)
    }

    suspend fun delete(food: Food) {
        return foodDao.delete(food)
    }

}