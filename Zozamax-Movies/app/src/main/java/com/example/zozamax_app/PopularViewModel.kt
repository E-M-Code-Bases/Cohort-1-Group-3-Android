package com.example.zozamax_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import com.example.zozamax_app.Data.models.Result

class PopularViewModel(val popularRepo:PopularRepository):ViewModel() {
    val popularPlayingMovies=MutableLiveData<List<Result>>(emptyList())
    init {
        getPopularPlayingMovies()
    }
    private fun getPopularPlayingMovies(){
        viewModelScope.launch {
            while (isActive){
                val response=popularRepo.getPopularMovies()
                if (response.isSuccessful){
                    if (response.body()!=null){
                        popularPlayingMovies.postValue(response.body()!!.results)
                    }
                }
            }
        }
    }


}
class PopularModelProvider(val popularRepo: PopularRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return PopularViewModel(popularRepo) as T
    }
}