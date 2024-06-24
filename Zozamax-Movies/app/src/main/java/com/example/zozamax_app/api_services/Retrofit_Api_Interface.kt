package com.example.zozamax_app.api_services


import com.example.zozamax_app.data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiInterface {

    @GET("movies")
    fun getMovies(
        @Query("page") page: Int
    ): retrofit2.Call<MovieResponse>
}
