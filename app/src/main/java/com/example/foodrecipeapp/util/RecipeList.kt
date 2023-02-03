package com.example.foodrecipeapp.util

import com.google.gson.annotations.SerializedName

//this is creted 3rd

data class RecipeResponse(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<Recipe?>?,
    @SerializedName("totalResults")
    val totalResults: Int?
)

data class Recipe(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("imageType")
    val imageType: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("results")
    val results: List<Detail?>?
)