package com.example.zozamax_app.viewmodel



import android.util.Log
import androidx.lifecycle.*
import com.example.zozamax_app.data.TrailerResult
import com.example.zozamax_app.repository.MovieDetailsRepo
import com.example.zozamax_app.data.Result
import com.example.zozamax_app.util.ACCOUNT_ID
import com.example.zozamax_app.util.API_KEY
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieDetailsRepo) : ViewModel() {

    private val _movieDetails = MutableLiveData<Result?>()
    val movie: LiveData<Result?> get() = _movieDetails

    private val _trailers = MutableLiveData<List<TrailerResult>>()
    val trailers: LiveData<List<TrailerResult>> get() = _trailers


    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite



    fun getMovieDetails(title: String): LiveData<Result?> {
        viewModelScope.launch {
            try {
                val movie = repository.getMovieByTitle(title)
                _movieDetails.postValue(movie)
                movie?.let {
                    _isFavorite.value = repository.isFavorite(ACCOUNT_ID, "Bearer $API_KEY", it.id)
                    getTrailer(it.id)
                }
            } catch (e: Exception) {
                Log.e("MovieDetailsViewModel", "Error fetching movie details: ${e.message}")
            }
        }
        return movie
    }

    fun getTrailer(movieId: Int) {
        viewModelScope.launch {
            try {
                val trailers = repository.getTrailers(movieId, "Bearer $API_KEY")
                _trailers.postValue(trailers)
            } catch (e: Exception) {
                Log.e("MovieDetailsViewModel", "Error fetching trailers: ${e.message}")
            }
        }
    }

    fun toggleFavorite(movie: Result) {
        viewModelScope.launch {
            try {
                val isFavorite = _isFavorite.value ?: false
                if (isFavorite) {
                    repository.removeFavorite(ACCOUNT_ID, "Bearer $API_KEY", movie.id)
                } else {
                    repository.addFavorite(ACCOUNT_ID, "Bearer $API_KEY", movie)
                }
                _isFavorite.value = !isFavorite
                Log.d("MovieDetailsViewModel", "Favorite status toggled: ${!isFavorite}")
            } catch (e: Exception) {
                Log.e("MovieDetailsViewModel", "Error toggling favorite status: ${e.message}")
            }
        }
    }

}


class MovieDetailsViewModelFactory(private val repository: MovieDetailsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}