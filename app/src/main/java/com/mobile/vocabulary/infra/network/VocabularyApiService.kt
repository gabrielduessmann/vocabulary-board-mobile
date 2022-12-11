package com.mobile.vocabulary.infra.network

import com.mobile.vocabulary.column.Column
import com.mobile.vocabulary.comment.Comment
import com.mobile.vocabulary.vocabulary.Vocabulary
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*


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

    @GET("vocabularies/column/{columnId}")
    fun getVocabulariesByColumnId(@Path("columnId") columnId: UUID): Call<List<Vocabulary>>

    @GET("vocabulary/{vocabularyId}/comments")
    fun getCommentsByVocabularyId(@Path("vocabularyId") vocabularyId: UUID): Call<List<Comment>>

    @POST("vocabulary")
    fun addVocabulary(@Body vocabulary: Vocabulary): Call<Vocabulary>

    @POST("comment")
    fun addComment(@Body comment: Comment): Call<Comment>

    @DELETE("comment/{id}")
    fun deleteCommentById(@Path("id") id: UUID): Call<Void>
}

object VocabularyApi {
    val retrofitService: VocabularyApiService by lazy {
        retrofit.create(VocabularyApiService::class.java)
    }
}