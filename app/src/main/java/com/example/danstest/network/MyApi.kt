package com.example.danstest.network;

import com.example.danstest.data.dto.recruitment.RecruitmentResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MyApi {
//
    @GET("recruitment/positions.json")
    suspend fun getListRecruitment(@QueryMap option:Map<String, String>): Response<RecruitmentResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {
            val okkhttpclient =
                UnsafeOkHtppClient.getUnsafeOkHttpClient(networkConnectionInterceptor)
            return Retrofit.Builder()
                .client(okkhttpclient)
                .baseUrl("http://dev3.dansmultipro.co.id/api/")
                .addConverterFactory(ToStringConvertFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}