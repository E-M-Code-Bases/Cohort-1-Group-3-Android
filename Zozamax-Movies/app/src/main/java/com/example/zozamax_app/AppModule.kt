package com.example.zozamax_app

import com.example.zozamax_app.Data.ApiInterface
import com.example.zozamax_app.utilities.Auth
import com.example.zozamax_app.utilities.base_url
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AppModule {

    fun getRetrofitInstance(): ApiInterface {
        val tokenInterceptor=TokenInterceptor(Auth)
        val client=OkHttpClient.Builder().addInterceptor(tokenInterceptor).build()
        val retrofit=Retrofit.Builder().baseUrl(base_url).client(client).addConverterFactory(
            GsonConverterFactory.create()).build()

        val apiService by lazy {
            retrofit.create(ApiInterface::class.java)
        }
        return apiService
    }
}
class TokenInterceptor(val token:String):Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request().newBuilder().addHeader("Authorization","Bearer $token").build()
        return chain.proceed(request)
    }
}