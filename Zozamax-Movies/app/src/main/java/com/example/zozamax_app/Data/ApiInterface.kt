package com.example.zozamax_app.Data

import com.example.zozamax_app.Data.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("movie/now_playing")
    suspend fun getNowPlaying():Response<ApiResponse>


}