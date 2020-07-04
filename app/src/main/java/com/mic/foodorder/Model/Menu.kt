package com.mic.foodorder.Model

data class Menu(
    val category: CategoryX,
    val created_at: String,
    val description: Any,
    val destination: String,
    val id: String,
    val menu_name: String,
    val menu_photo: String,
    val menu_price: String,
    val updated_at: String,
    val user_detail: UserDetail
)