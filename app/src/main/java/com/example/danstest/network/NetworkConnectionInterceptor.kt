package com.example.danstest.network;

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.example.danstest.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val appcontex = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        var builder: Request.Builder = chain.request().newBuilder()

        if (!isIInternetisAvelible()) {
            throw NoInternetException("Pastiken Internet Anda Terhubung")
        }
        return chain.proceed(builder.build())
    }

    @SuppressLint("MissingPermission")
    private fun isIInternetisAvelible(): Boolean {
        val connectivityManager =
            appcontex.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }


}