package com.example.foodrecipeapp.ui.detail

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.repository.Repository
import com.example.foodrecipeapp.util.BaseViewModel
import com.example.foodrecipeapp.util.Detail
import com.example.foodrecipeapp.util.NetworkResult
import com.example.foodrecipeapp.util.NutrientX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val repository: Repository,
    application: Application
) : BaseViewModel(application) {
    private val _myResponceData = MutableLiveData<NetworkResult<Detail>>()
    val myResponceData: LiveData<NetworkResult<Detail>>
        get() = _myResponceData

    private val mContext = application


    private var vid = -1

    @RequiresApi(Build.VERSION_CODES.M)
    fun getRecipe2(id: Int) {
        if (vid == id) {
            return
        } else {
            vid = id
        }
        viewModelScope.launch {

            if (isConnected()) {


                _myResponceData.value = NetworkResult.Loading()
                val response: Response<Detail> = repository.getSingleRecipe(id)
                _myResponceData.value = handleResponse(response)


            } else {
                Toast.makeText(mContext, R.string.no_internet, Toast.LENGTH_SHORT).show()
            }


        }
    }

}
