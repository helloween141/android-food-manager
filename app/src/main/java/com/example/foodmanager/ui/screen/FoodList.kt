package com.example.foodmanager.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodmanager.ui.viewmodel.FoodViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalMaterialApi::class)
@InternalCoroutinesApi
@Composable
fun FoodList(
    vm: FoodViewModel,
    nav: NavHostController
) {
    val foodList = vm.foodList.observeAsState().value
    val totalPrice = vm.totalPrice.observeAsState().value

    Surface(modifier = Modifier.fillMaxSize()) {
        BottomSheetScaffold(sheetContent = {
            Row(modifier = Modifier.align(CenterHorizontally)) {
                Text(
                    text = "Итого: $totalPrice руб.",
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                )
                Button(modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                    onClick = { nav.navigate("create") }) {
                    Text(text = "Создать товар")
                }
            }
        }) {
            LazyColumn(modifier = Modifier.padding(15.dp)) {
                items(items = foodList!!) { item ->
                    Row(modifier = Modifier.background(Color.White)) {
                        Text(text = item.name, modifier = Modifier.weight(1f))
                        Text(text = "${item.price} руб.", modifier = Modifier.weight(1f))
                        Checkbox(
                            checked = item.checked,
                            onCheckedChange = {
                                item.checked = !item.checked
                                vm.updateFood(item)
                            },
                            modifier = Modifier.weight(0.4f)
                        )
                        IconButton(
                            onClick = { nav.navigate("food/${item.id}") },
                            modifier = Modifier
                                .weight(0.4f)
                                .then(Modifier.size(24.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "Редактировать"
                            )
                        }
                        IconButton(
                            onClick = { vm.deleteFood(item) },
                            modifier = Modifier
                                .weight(0.4f)
                                .then(Modifier.size(24.dp))
                        ) {
                            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Удалить")
                        }
                    }
                }
            }
        }
    }
}