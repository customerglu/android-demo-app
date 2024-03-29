package com.example.customerglu.Model

data class Product (
    val productName:String = "",
    val productId:String = "",
    val productPrice:String = "",
    val productDes:String = "",
    val productRating: Float = 0.0F,
    val productDisCount:String = "",
    val productHave:Boolean = false,
    val productBrand:String = "",
    val productImage:String = "",
    val productCategory:String = "",
    val isFavourite:Boolean,
    val productNote:String = "",
    val productFrom:String = "",
)