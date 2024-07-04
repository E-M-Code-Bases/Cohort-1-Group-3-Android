package com.example.zozamax_app.Data

import com.example.zozamax_app.Data.models.ApiResponse
import com.example.zozamax_app.Data.models.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("movie/now_playing")
    suspend fun getNowPlaying():Response<ApiResponse>
    @GET("movie/popular")
    suspend fun getPopularMovies():Response<ApiResponse>
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies():Response<ApiResponse>
    @GET("movie/upcoming")
    suspend fun getUpComingMovies():Response<ApiResponse>
    @POST("account/{account_id}/favorite")
    suspend fun addFavorites():Response<ApiResponse>
    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies():Response<ApiResponse>
    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(@Path("movie_id") id: Int):Response<VideoResponse>


}