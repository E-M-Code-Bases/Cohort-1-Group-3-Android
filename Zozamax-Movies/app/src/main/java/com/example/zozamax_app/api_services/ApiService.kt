package com.example.zozamax_app.api_services

import com.example.zozamax_app.data.FavoriteRequest
import com.example.zozamax_app.data.MovieApiResponse
import com.example.zozamax_app.data.TrailerApiResponse
import com.example.zozamax_app.data.TrailerResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query



interface  ApiService {
    @GET("3/account/21323729/rated/movies")
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OWNjMTMyZGJhODM0ZTRlNTZlYWZkYWNhMzgyYjJkZCIsInN1YiI6IjY2NjkzYzE2ZTcxMDM0MDEwZmJlYWE1OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.s2yy6z0a1ZRt05q25ZZ34dugk3MTedmTOUfkzvMb_uE"
    )
    fun getRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "created_at.asc"
    ): Response<MovieApiResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String="en-US",
        @Query("page") page: Int =1
    ): Response<MovieApiResponse>

    //getting movie images
    @GET("")
    suspend fun getMovieImage()

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String="en-US",
        @Query("page") page: Int =1
    ): Response<MovieApiResponse>


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(

        @Query("language") language: String="en-US",
        @Query("page") page: Int =1
    ): Response<MovieApiResponse>


    @GET("tv/on_the_air")
    suspend fun getOnTvMovies(

        @Query("language") language: String="en-US",
        @Query("page") page: Int =1
    ): Response<MovieApiResponse>


    @GET("movie/top_rated")
    suspend fun geTopMovies(
        @Query("language") language: String="en-US",
        @Query("page") page: Int =1
    ): Response<MovieApiResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailers(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String): Response<TrailerApiResponse>

    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: String,
        @Header("Authorization") authHeader: String,
//        @Query("language") language: String = "en-US",
//        @Query("sort_by") sortBy: String = "created_at.asc",
//        @Query("page") page: Int = 1
    ): Response<MovieApiResponse>

    @GET("account/{account_id}/favorite/movies")
    suspend fun isFavorite(
        @Path("account_id") accountId: String,
        @Header("Authorization") authHeader: String,
        @Query("movie_id") movieId: Int
    ): Response<MovieApiResponse>

    @GET("movie/trailers")
    suspend fun getTrailers(@Query("page") i: Int = 1): Response<MovieApiResponse>

    @POST("account/{account_id}/favorite")
    suspend fun markAsFavorite(
        @Path("account_id") accountId: String,
        @Header("Authorization") authHeader: String,
        @Body favoriteRequest: FavoriteRequest
    ): Response<Unit>

    @GET("search/movie")
    suspend fun getMovieByTitle(@Query("query") title: String): Response<MovieApiResponse>


}



