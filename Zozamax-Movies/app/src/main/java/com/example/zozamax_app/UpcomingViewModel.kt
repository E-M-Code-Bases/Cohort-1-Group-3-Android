package com.example.zozamax_app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.zozamax_app.Data.models.Result
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class UpcomingViewModel(val upComingRepo:UpcomingRepository):ViewModel() {
    val upComingMovies=MutableLiveData<List<Result>>(emptyList())
    init {
        getUpComingMovies()
    }
    private fun getUpComingMovies(){
        viewModelScope.launch {
            while (isActive){
                val response=upComingRepo.getUpComingMovies()
                if (response.isSuccessful){
                    if (response.body()!=null){

                        upComingMovies.postValue(response.body()!!.results)

                    }
                }
            }
        }
    }
}
class UpComingModelProvider(val upComingRepo: UpcomingRepository):ViewModelProvider.Factory{
    override fun<T:ViewModel>create(modelClass: Class<T>,extras: CreationExtras):T{
        return UpcomingViewModel(upComingRepo) as T
    }
}