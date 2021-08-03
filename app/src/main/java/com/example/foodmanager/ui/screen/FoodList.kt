package com.example.foodmanager.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foodmanager.ui.theme.Purple200
import com.example.foodmanager.ui.theme.Shapes
import com.example.foodmanager.ui.viewmodel.FoodViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalMaterialApi::class)
@InternalCoroutinesApi
@Composable
fun FoodList(
    vm: FoodViewModel,
    nav: NavHostController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        BottomSheetScaffold(sheetContent = {
            Row(modifier = Modifier.align(CenterHorizontally)) {
                Text(text = "Итого: 0 руб.", modifier = Modifier.padding(10.dp).weight(1f))
                Button(modifier = Modifier.padding(10.dp).weight(1f),
                    onClick = { nav.navigate("create") }) {
                    Text(text = "Создать товар")
                }
            }
        }) {
            Column(modifier = Modifier.padding(15.dp)) {
                Row() {
                    Text(text = "Some food 1", modifier = Modifier.weight(1f))
                    Text(text = "100$", modifier = Modifier.weight(1f))
                    Checkbox(checked = false, onCheckedChange = {})
                }
                Row() {
                    Text(text = "Some food 2", modifier = Modifier.weight(1f))
                    Text(text = "130$", modifier = Modifier.weight(1f))
                    Checkbox(checked = false, onCheckedChange = {})
                }
                Row() {
                    Text(text = "Some food 3", modifier = Modifier.weight(1f))
                    Text(text = "50$", modifier = Modifier.weight(1f))
                    Checkbox(checked = false, onCheckedChange = {})
                }
            }
        }
    }
}