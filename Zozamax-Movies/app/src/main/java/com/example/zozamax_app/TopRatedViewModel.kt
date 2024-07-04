package com.example.zozamax_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.zozamax_app.Data.models.Result
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class TopRatedViewModel(val topRatedRepo:TopRatedRepository):ViewModel() {
    val topRatedMovies=MutableLiveData<List<Result>>(emptyList())
    init {
        getTopRatedMovies()
    }
    private fun getTopRatedMovies(){
        viewModelScope.launch {
            while (isActive){
                val response=topRatedRepo.getTopRatedMovies()
                if (response.isSuccessful){
                    if (response.body()!=null){
                        topRatedMovies.postValue(response.body()!!.results)
                    }
                }
            }
        }

    }
}
class TopRatedModelProvider(val topRatedRepo: TopRatedRepository):ViewModelProvider.Factory{
    override fun <T :ViewModel> create(modelClass: Class<T>,extras: CreationExtras):T{
        return TopRatedViewModel(topRatedRepo)as T
    }
}