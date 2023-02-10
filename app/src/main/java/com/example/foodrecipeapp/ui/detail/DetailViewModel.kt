package com.example.foodrecipeapp.ui.detail

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
    val myResponce2: MutableLiveData<NetworkResult<Detail>> = MutableLiveData()
    val mycarbs: MutableLiveData<NutrientX> = MutableLiveData()
    private val mContext = application

    //    val fat : MutableLiveData<NutrientX> = MutableLiveData()
//    var f1 by Delegates.notNull<Double>()
    @RequiresApi(Build.VERSION_CODES.M)
    fun getRecipe2(id: Int) {

        viewModelScope.launch {

            if (isConnected()) {


                    myResponce2.value = NetworkResult.Loading()
                val response2: Response<Detail> = repository.getSingleRecipe(id)
                    myResponce2.value = handleResponse(response2)



            } else {
                Toast.makeText(mContext, R.string.no_internet, Toast.LENGTH_SHORT).show()
            }


//            if(mycarbs.value?.name == "Fat") {
//                f1 = mycarbs!!.value!!.amount!!
        }
    }

}
