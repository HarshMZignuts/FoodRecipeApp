package com.example.foodrecipeapp.ui.overview

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodrecipeapp.R

import com.example.foodrecipeapp.repository.Repository
import com.example.foodrecipeapp.util.BaseViewModel
import com.example.foodrecipeapp.util.NetworkResult

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
    application: Application) : BaseViewModel(application){
    //val myResponce4: MutableLiveData<RecipeResponse> = MutableLiveData()
//    val myRecipeResponce: MutableLiveData<RecipeResponse> = MutableLiveData()
    val myRecipeResponce: MutableLiveData<NetworkResult<RecipeResponse>> = MutableLiveData()
   // val myresponce : MutableLiveData<Detail> = MutableLiveData()
    private val mContext = application
    @RequiresApi(Build.VERSION_CODES.M)
    fun getQurryRecipe2(query:String){

            viewModelScope.launch {
                myRecipeResponce.value = NetworkResult.Loading()

                if (isConnected()){


                        val response4 : Response<RecipeResponse> = repository.getQurryRecipe(query)
                        myRecipeResponce.value = handleResponse(response4)


                }
                else {
                    myRecipeResponce.value = NetworkResult.Error(
                        mContext.getString(R.string.no_internet)
                    )
                }


            }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getAllRecipe2(){

            viewModelScope.launch {
                myRecipeResponce.value = NetworkResult.Loading()

                if (isConnected()){

                        val response5 : Response<RecipeResponse> = repository.getAllRecipe()

                        myRecipeResponce.value = handleResponse(response5)


                }
                else{
                    myRecipeResponce.value = NetworkResult.Error(
                        mContext.getString(R.string.no_internet)
                    )
                }

            }

    }

}