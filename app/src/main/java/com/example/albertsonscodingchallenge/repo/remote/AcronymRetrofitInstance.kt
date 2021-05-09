package com.example.albertsonscodingchallenge.repo.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AcronymRetrofitInstance {
    private const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"

    private val client = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }
        .let { OkHttpClient.Builder().addInterceptor(it).build() }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    val acronymService: AcronymService by lazy { retrofit.create(AcronymService::class.java) }
}
