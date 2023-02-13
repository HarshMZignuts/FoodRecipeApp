package com.example.foodrecipeapp.api

import com.example.foodrecipeapp.util.Detail

import com.example.foodrecipeapp.util.Recipe
import com.example.foodrecipeapp.util.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {
    @GET("recipes/716429/information?includeNutrition=false&apiKey=5e6e52beecef465c8e9fe1b0d61e8841")
    suspend fun getRecipe() : Response<Recipe>

    @GET("recipes/{id}/information?includeNutrition=true&apiKey=5e6e52beecef465c8e9fe1b0d61e8841")
    suspend fun getRecipe2(
        @Path("id") id:Int
    ): Response<Detail>

    @GET("recipes/complexSearch?apiKey=5e6e52beecef465c8e9fe1b0d61e8841&number=20")
    suspend fun getQurryRecipe(
        @Query("query") query:String
    ): Response<RecipeResponse>

    @GET("recipes/complexSearch?apiKey=5e6e52beecef465c8e9fe1b0d61e8841&number=20")
    suspend fun getAllRecipe() : Response<RecipeResponse>
    //&query=ramen
}

