package com.example.foodmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodmanager.data.entity.Food
import com.example.foodmanager.domain.FoodRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class FoodViewModel @Inject constructor(private val repository: FoodRepositoryImpl) : ViewModel() {
    private var _foodList = MutableLiveData<List<Food>>(listOf())

    val foodList: LiveData<List<Food>>
        get() = _foodList

    init {
        viewModelScope.launch {
           repository.getAll().collect {
               _foodList.postValue(it)
           }
        }
    }

    suspend fun addFood(name: String, price: String) {
        val food = Food(name = name, price = price.toDouble(), status = false)
        viewModelScope.launch {
            repository.create(food)
        }
    }

    fun showProgress() {

    }
}