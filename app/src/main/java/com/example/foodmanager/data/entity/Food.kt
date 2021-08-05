package com.example.foodmanager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    var name: String,
    var price: Double,
    val checked: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}