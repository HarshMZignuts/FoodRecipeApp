package com.example.foodrecipeapp.api

import com.example.foodrecipeapp.util.Detail
import com.example.foodrecipeapp.util.Recipe
import com.example.foodrecipeapp.util.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {
    @GET("recipes/716429/information?includeNutrition=false&apiKey=da3197b4102241f8ae2fcc793d2fa878")
    suspend fun getRecipe() : Response<Recipe>

    @GET("recipes/{id}/information?includeNutrition=true&apiKey=da3197b4102241f8ae2fcc793d2fa878")
    suspend fun getRecipe2(
        @Path("id") id:Int
    ): Response<Detail>
    @GET("recipes/complexSearch?apiKey=da3197b4102241f8ae2fcc793d2fa878")
    suspend fun getQurryRecipe(
        @Query("query") query:String
    ): Response<RecipeResponse>
    @GET("recipes/complexSearch?apiKey=da3197b4102241f8ae2fcc793d2fa878&query=icecream&number=20")
    suspend fun getAllRecipe() : Response<RecipeResponse>
}