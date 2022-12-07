package com.mobile.vocabulary.infra.network

import com.mobile.vocabulary.column.Column
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "http://192.168.0.2:8080"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface VocabularyApiService {

    @GET("columns")
    fun getColumns(): Call<List<Column>>
    // suspend fun getColumns(): Call<List<Column>> - Why suspend?
}

object VocabularyApi {
    val retrofitService: VocabularyApiService by lazy {
        retrofit.create(VocabularyApiService::class.java)
    }
}