package com.example.foodrecipeapp.ui.overview

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipeapp.R

import com.example.foodrecipeapp.repository.Repository
import com.example.foodrecipeapp.util.Recipe
import com.example.foodrecipeapp.util.RecipeResponse

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

//this is creted 4th
@HiltViewModel
class OverviewViewModel
@Inject
constructor(
    private val repository:Repository,
    application: Application) : ViewModel(){
    val myResponce4: MutableLiveData<RecipeResponse> = MutableLiveData()
    val myResponce5: MutableLiveData<RecipeResponse> = MutableLiveData()
    private val mContext = application
    fun getQurryRecipe2(){
        viewModelScope.launch {
            viewModelScope.launch {
                val response4 : Response<RecipeResponse> = repository.getQurryRecipe(query = "pizza")
                if (response4.isSuccessful){
                    response4.body().let {
                        myResponce4.value = it
                    }
                }

            }
        }
    }
    fun getAllRecipe2(){

            viewModelScope.launch {
                val response5 : Response<RecipeResponse> = repository.getAllRecipe()
                if (response5.isSuccessful){
                    response5.body().let {
                        myResponce5.value = it
                    }
                }
                else{
                    Toast.makeText(mContext,R.string.no_internet,Toast.LENGTH_SHORT).show()
                }

            }

    }

}