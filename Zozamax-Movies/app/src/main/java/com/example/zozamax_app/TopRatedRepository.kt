package com.example.zozamax_app

import com.example.zozamax_app.Data.models.ApiResponse
import retrofit2.Response

class TopRatedRepository {
    val retrofit=AppModule().getRetrofitInstance()
    suspend fun getTopRatedMovies():Response<ApiResponse>{
        return retrofit.getTopRatedMovies()
    }
}