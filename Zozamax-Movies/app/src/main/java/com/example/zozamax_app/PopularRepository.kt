package com.example.zozamax_app

import com.example.zozamax_app.Data.models.ApiResponse
import retrofit2.Response

class PopularRepository {
    val retrofit=AppModule().getRetrofitInstance()
    suspend fun getPopularMovies(): Response<ApiResponse> {
        return retrofit.getPopularMovies()

    }
}