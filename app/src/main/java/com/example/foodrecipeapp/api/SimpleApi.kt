package com.example.foodrecipeapp.api

import com.example.foodrecipeapp.util.Detail
import com.example.foodrecipeapp.util.Recipe
import com.example.foodrecipeapp.util.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {
    @GET("recipes/716429/information?includeNutrition=false&apiKey=f8e4cf94f97547e6ab279071c4a09ccc")
    suspend fun getRecipe() : Response<Recipe>

    @GET("recipes/{id}/information?includeNutrition=true&apiKey=f8e4cf94f97547e6ab279071c4a09ccc")
    suspend fun getRecipe2(
        @Path("id") id:Int
    ): Response<Detail>
    @GET("recipes/complexSearch?apiKey=f8e4cf94f97547e6ab279071c4a09ccc")
    suspend fun getQurryRecipe(
        @Query("query") query:String
    ): Response<RecipeResponse>
    @GET("recipes/complexSearch?apiKey=f8e4cf94f97547e6ab279071c4a09ccc&number=20&query=pasta")
    suspend fun getAllRecipe() : Response<RecipeResponse>
    //&query=ramen
}