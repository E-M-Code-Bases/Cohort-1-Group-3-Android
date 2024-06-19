package com.example.zozamax_app.api_services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("3/account/21323729/favorite/movies")
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OWNjMTMyZGJhODM0ZTRlNTZlYWZkYWNhMzgyYjJkZCIsInN1YiI6IjY2NjkzYzE2ZTcxMDM0MDEwZmJlYWE1OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.s2yy6z0a1ZRt05q25ZZ34dugk3MTedmTOUfkzvMb_uE"
    )
    fun getFavoriteMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "created_at.asc"
    ): Call<MovieResponse>
}
