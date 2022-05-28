package com.example.danstest.network;

import com.example.danstest.util.ApiException
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val message = StringBuilder()
            if (response.code() == 500) {
                message.append("Kesalahan System")
            } else if (response.code() == 401) {
                message.append("Anda Tidak Memiliki Akses")
            } else if (response.code() == 404) {
                message.append("Not Found")
            } else if (response.code() == 400) {
                message.append("Bad Request")
            } else if (response.code() == 429) {
                message.append("Too Many Requests")
            }
            throw ApiException(message.toString())
        }
    }


}