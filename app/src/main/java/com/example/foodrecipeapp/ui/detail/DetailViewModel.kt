package com.example.foodrecipeapp.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.foodrecipeapp.repository.Repository
import com.example.foodrecipeapp.util.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val repository: Repository,
    application: Application
):ViewModel()
{
    val myResponce2: MutableLiveData<Response<Detail>> = MutableLiveData()
    fun getRecipe2(id: Int) {

        viewModelScope.launch {
            val response2: Response<Detail> = repository.getRecipe2(id)
            myResponce2.value = response2
        }

    }
}