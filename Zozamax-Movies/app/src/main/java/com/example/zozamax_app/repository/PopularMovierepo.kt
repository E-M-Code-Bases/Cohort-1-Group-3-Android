package com.example.zozamax_app.repository

import com.example.zozamax_app.AppModule
import com.example.zozamax_app.data.MovieApiResponse
import retrofit2.Response

class PopularMovierepo() {
    suspend fun getPopulaMovies(): Response<MovieApiResponse> {
        val retrofit = AppModule().getRetrofitInstance()
        return retrofit.getPopularMovies()
    }
}