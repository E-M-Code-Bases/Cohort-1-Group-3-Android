package com.example.zozamax_app.repository

import android.graphics.Movie
import com.example.zozamax_app.AppModule
import com.example.zozamax_app.api_services.ApiService
import com.example.zozamax_app.data.FavoriteRequest
import com.example.zozamax_app.data.Result
import com.example.zozamax_app.data.TrailerResult
import retrofit2.HttpException


class MovieDetailsRepo(private val apikey: String) {
    private val apiService: ApiService = AppModule().getRetrofitInstance()

    suspend fun getMovieByTitle(title: String): Result? {
        val response = apiService.getMovieByTitle(title)
        return if (response.isSuccessful) {
            response.body()?.results?.firstOrNull { it.title == title }
        } else {
            null
        }
    }

    suspend fun getTrailers(movieId: Int, authHeader: String): List<TrailerResult> {
        val response = apiService.getTrailers(movieId, authHeader)
        return if (response.isSuccessful) {
            response.body()?.trailerResults ?: emptyList()
        } else {
            throw HttpException(response)
        }
    }

    suspend fun isFavorite(accountId: String, authHeader: String, movieId: Int): Boolean {
        val response = apiService.getFavoriteMovies(accountId, authHeader)
        if (response.isSuccessful) {
            val favoriteMovies = response.body()?.results ?: emptyList()
            return favoriteMovies.any { it.id == movieId }
        }
        return false
    }

    suspend fun addFavorite(accountId: String, authHeader: String, movie: Result) {
        val request = FavoriteRequest(mediaType = "movie", mediaId = movie.id, favorite = true)
        val response = apiService.markAsFavorite(accountId, authHeader, request)
        if (!response.isSuccessful) throw HttpException(response)
    }

    suspend fun removeFavorite(accountId: String, authHeader: String, movieId: Int) {
        val request = FavoriteRequest(mediaType = "movie", mediaId = movieId, favorite = false)
        val response = apiService.markAsFavorite(accountId, authHeader, request)
        if (!response.isSuccessful) throw HttpException(response)
    }
}
