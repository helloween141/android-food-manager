package com.example.foodmanager.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foodmanager.ui.viewmodel.FoodViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@Composable
fun FoodItem(
    vm: FoodViewModel,
    nav: NavHostController,
    id: Int = 0
) {
    val coroutineScope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopAppBar() {
                IconButton(onClick = { nav.navigate("list") }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Назад")
                }
                if (id == 0) {
                    Text(text = "Новый товар", modifier = Modifier.padding(15.dp))
                } else {
                    Text(text = "Редактировать товар", modifier = Modifier.padding(15.dp))
                }
            }

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                label = { Text("Название") }
            )

            OutlinedTextField(
                value = price,
                onValueChange = {
                    price = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                label = { Text("Цена") }
            )

            Button(
                onClick = {
                    if (id == 0) {
                        coroutineScope.launch {
                            vm.addFood(name, price)
                            name = ""
                            price = ""
                        }
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