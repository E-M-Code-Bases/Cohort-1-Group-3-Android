package com.example.zozamax_app

import com.example.zozamax_app.Data.models.ApiResponse
import retrofit2.Response

class UpcomingRepository {
    val retrofit=AppModule().getRetrofitInstance()
    suspend fun getUpComingMovies():Response<ApiResponse>{
        return retrofit.getUpComingMovies()
    }
}