package com.example.zozamax_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.zozamax_app.Data.models.Result
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MovieViewModel(val repo:NowPlayingRepository):ViewModel() {
    val nowPlayingMovies=MutableLiveData<List<Result>>(emptyList())

    init {
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies(){
        viewModelScope.launch {
            while (isActive){
                val response = repo.getNowPlaying()
                if (response.isSuccessful){
                    if (response.body() != null){
                        nowPlayingMovies.postValue(response.body()!!.results)
                    }
                }
            }
        }
    }
}

class ModelProvider(val repo: NowPlayingRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MovieViewModel(repo) as T
    }
}