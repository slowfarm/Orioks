package ru.eva.oriokslive.network.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(private val accessToken: String?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
            .header("Accept", "application/json")
            .header("User-Agent", "orioks/2.0 android ${android.os.Build.VERSION.RELEASE}")
            .method(original.method, original.body)
        accessToken?.let { builder.header("Authorization", "Bearer $accessToken") }
        return chain.proceed(builder.build())
    }
}
