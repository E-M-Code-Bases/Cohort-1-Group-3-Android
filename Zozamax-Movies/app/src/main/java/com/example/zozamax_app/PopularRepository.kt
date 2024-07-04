package com.example.zozamax_app

import com.example.zozamax_app.Data.models.ApiResponse
import com.example.zozamax_app.Data.models.VideoResponse
import retrofit2.Response

class PopularRepository {
    val retrofit=AppModule().getRetrofitInstance()

    suspend fun getPopularMovies(): Response<ApiResponse> {
        return retrofit.getPopularMovies()

    }
    suspend fun getVideos(id: Int):Response<VideoResponse>{
        return retrofit.getVideos(id)
    }

}