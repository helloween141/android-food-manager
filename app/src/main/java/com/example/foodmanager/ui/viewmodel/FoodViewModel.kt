package com.example.foodmanager.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    private var _totalPrice = MutableLiveData(0.0)
    val totalPrice: LiveData<Double>
        get() = _totalPrice

    var showProgress: MutableState<Boolean> = mutableStateOf(false)
    var name: MutableState<String> = mutableStateOf("")
    var price: MutableState<String> = mutableStateOf("")

    init {
        viewModelScope.launch {
            repository.getAll().collect { it ->
                _foodList.postValue(it)

                val sum = it.sumOf { elem -> if (!elem.checked) elem.price else 0.0 }
                _totalPrice.postValue(sum)
            }
        }
    }

    fun getFood(id: Int = 0) {
        if (id > 0) {
            viewModelScope.launch {
                showProgress.value = true
                repository.getById(id).collect {
                    name.value = it.name
                    price.value = it.price.toString()
                    showProgress.value = false
                }
            }
        }
    }

    fun addFood() {
        val food = Food(name = name.toString(), price = price.toString().toDouble(), checked = false)
        viewModelScope.launch {
            repository.create(food)
        }
    }

    fun deleteFood(food: Food) {
        viewModelScope.launch {
            repository.delete(food)
        }
    }
}