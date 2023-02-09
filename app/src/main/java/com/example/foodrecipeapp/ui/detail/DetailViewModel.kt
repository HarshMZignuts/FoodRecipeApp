package com.example.foodrecipeapp.ui.detail

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.repository.Repository
import com.example.foodrecipeapp.util.Detail
import com.example.foodrecipeapp.util.NutrientX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val repository: Repository,
    application: Application
) : ViewModel() {
    val myResponce2: MutableLiveData<Response<Detail>> = MutableLiveData()
    val mycarbs: MutableLiveData<NutrientX> = MutableLiveData()
    private val mContext = application

    //    val fat : MutableLiveData<NutrientX> = MutableLiveData()
//    var f1 by Delegates.notNull<Double>()
    fun getRecipe2(id: Int) {

        viewModelScope.launch {
            val response2: Response<Detail> = repository.getRecipe2(id)
            if (response2.isSuccessful) {
                myResponce2.value = response2
            } else {
                Toast.makeText(mContext, R.string.no_internet, Toast.LENGTH_SHORT).show()
            }


//            if(mycarbs.value?.name == "Fat") {
//                f1 = mycarbs!!.value!!.amount!!
        }
    }

}
