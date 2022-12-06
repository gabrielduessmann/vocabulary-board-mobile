package com.mobile.vocabulary.infra.http

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        const val BASE_URL: String = "teste"
        var retrofit: Retrofit? = null

        fun getRetrofitClient(): ApiInterface? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(ApiInterface::class.java)
        }
    }
}