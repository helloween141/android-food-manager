package com.example.foodmanager.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodmanager.ui.screen.FoodItem
import com.example.foodmanager.ui.screen.FoodList
import com.example.foodmanager.ui.theme.FoodManagerTheme
import com.example.foodmanager.ui.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @InternalCoroutinesApi
    private val vmFood: FoodViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }

    @InternalCoroutinesApi
    @Composable
    fun AppNavigator() {
        val navController = rememberNavController()
        FoodManagerTheme {
            NavHost(navController = navController, startDestination = "list") {
                composable("list") { FoodList(vm = vmFood, nav = navController) }
                composable("create") { FoodItem(vm = vmFood, nav = navController) }
                composable("food/{id}") { backStackEntry ->
                    backStackEntry.arguments?.getString("id")?.let { it -> FoodItem(vm = vmFood, nav = navController, it.toInt()) }
                }
            }
        }
    }
}
