package com.example.foodrecipeapp.repository

import com.example.foodrecipeapp.api.SimpleApi
import com.example.foodrecipeapp.util.Detail
import com.example.foodrecipeapp.util.NetworkResult
import com.example.foodrecipeapp.util.Recipe
import com.example.foodrecipeapp.util.RecipeResponse
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api : SimpleApi
) {
    suspend fun getRecipe() : Response<Recipe> {
        return api.getRecipe()
    }

    suspend fun getSingleRecipe(id:Int): Response<Detail> {
        return api.getRecipe2(id)
    }
    suspend fun getQurryRecipe(query:String): Response<RecipeResponse>
    {
        return api.getQurryRecipe(query)
    }
    suspend fun getAllRecipe() : Response<RecipeResponse>
    {
        return api.getAllRecipe()
    }
}