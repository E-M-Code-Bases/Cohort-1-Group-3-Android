package com.example.zozamax_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zozamax_app.repository.PopularMovierepo
import com.example.zozamax_app.data.Result
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class PopularViewModel(val repo: PopularMovierepo): ViewModel() {
    var popularMovies = MutableLiveData<List<Result>>(emptyList())
    fun getPopularMovies (){
        viewModelScope.launch {
            while (isActive){
                try {
                    val response = repo.getPopulaMovies()
                    if (response.isSuccessful){
                        if (response.body()!!.results.isNotEmpty()){
                            popularMovies.postValue(response.body()!!.results)
                        }

                }
            }catch (t:Throwable){
                println(t)
            }            }
        }
    }

}