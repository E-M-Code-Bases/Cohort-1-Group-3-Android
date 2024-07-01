package com.example.zozamax_app

import com.example.zozamax_app.Data.models.ApiResponse
import retrofit2.Response

class NowPlayingRepository {
    val retrofit=AppModule().getRetrofitInstance()
    suspend fun getNowPlaying(): Response<ApiResponse> {
        return retrofit.getNowPlaying()
    }
}