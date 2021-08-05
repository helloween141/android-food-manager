package com.example.foodmanager.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foodmanager.ui.viewmodel.FoodViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@InternalCoroutinesApi
@Composable
fun FoodItem(
    vm: FoodViewModel,
    nav: NavHostController,
    id: Int = 0
) {
    val coroutineScope = rememberCoroutineScope()
    val showProgress = vm.showProgress
    val name = vm.name
    val price = vm.price

    if (id > 0) {
        coroutineScope.launch {
            vm.getFood(id)
        }
    }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopAppBar() {
                IconButton(onClick = { nav.navigate("list") }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Назад")
                }
                if (id == 0) {
                    Text(text = "Новый товар", modifier = Modifier.padding(15.dp))
                } else {
                    Text(text = "Редактировать ${name.value}", modifier = Modifier.padding(15.dp))
                }
                if (showProgress.value) {
                    CircularProgressIndicator(modifier = Modifier.background(Color.White))
                }
            }
            Column() {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = {
                        name.value = it
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    label = { Text("Название") }
                )

                OutlinedTextField(
                    value = price.value,
                    onValueChange = {
                        price.value = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    label = { Text("Цена") }
                )

                Button(
                    onClick = {
                        coroutineScope.launch {
                            vm.addFood()
                        }
                    }, modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Добавить")
                }
            }
        }
    }
}