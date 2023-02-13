package com.example.foodrecipeapp.ui.overview

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
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
import timber.log.Timber
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.M)
@HiltViewModel
class OverviewViewModel
@Inject
constructor(
    private val repository: Repository,
    application: Application
) : BaseViewModel(application) {

    private val _myRecipeResponce = MutableLiveData<NetworkResult<RecipeResponse>>()
    val myRecipeResponce: LiveData<NetworkResult<RecipeResponse>>
        get() = _myRecipeResponce

    init {
       // Timber.e("init")
        getAllRecipe2()
    }


    private val mContext = application

    @RequiresApi(Build.VERSION_CODES.M)
    fun getQurryRecipe2(query: String) {

        viewModelScope.launch {


            if (isConnected()) {
                _myRecipeResponce.value = NetworkResult.Loading()
                val response: Response<RecipeResponse> = repository.getQurryRecipe(query)
                _myRecipeResponce.value = handleResponse(response)


            } else {
                _myRecipeResponce.value = NetworkResult.Error(
                    context.resources.getString(R.string.no_internet)
                )
            }


        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getAllRecipe2() {

        viewModelScope.launch {


            if (isConnected()) {
                _myRecipeResponce.value = NetworkResult.Loading()
                val response: Response<RecipeResponse> = repository.getAllRecipe()
                _myRecipeResponce.value = handleResponse(response)


            }
            else {
                _myRecipeResponce.value = NetworkResult.Error(
                    context.resources.getString(R.string.no_internet)
                )
            }

        }

    }

}