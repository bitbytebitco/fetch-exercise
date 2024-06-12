package com.example.fetchexercise.network

import com.example.fetchexercise.model.JsonItem
import retrofit2.Retrofit
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

/** API url */
private const val BASE_URL =
        "https://fetch-hiring.s3.amazonaws.com"

/** creating the retrofit object */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/** interface that defines how the asynchronous function calls the API endpoint */
interface FetchApiService {
    @GET("hiring.json")
    suspend fun getFetchJson() : List<JsonItem>
}

/** Public object to access the Retrofit service */
object FetchApi {
    val retrofitService : FetchApiService by lazy {
        retrofit.create(FetchApiService::class.java)
    }
}
