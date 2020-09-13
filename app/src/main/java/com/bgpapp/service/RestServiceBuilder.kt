package com.bgpapp.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestServiceBuilder {

    private val url = "http://212.191.87.173/strangers/api/"

    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request =
            chain.request().newBuilder().addHeader("Authorization", "Bearer ${Config.token}")
                .build()
        chain.proceed(request)
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun <T> build(service: Class<T>): T {
        return retrofit.create(service)
    }

}