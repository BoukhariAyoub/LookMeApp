package com.lookmyup.app.data.remote.entities

data class FashionItemResponse(
    val name: String,
    val imageUrl: String,
    val shoppingUrl: String,
    val tag: String?
)