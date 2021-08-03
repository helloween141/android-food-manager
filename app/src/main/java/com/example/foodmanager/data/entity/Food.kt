package com.example.foodmanager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    val name: String,
    val price: Double,
    val status: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}