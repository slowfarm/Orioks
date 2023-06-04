package ru.eva.oriokslive.network.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class MietInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
            .header("Cookie", "wl=59155c99d474438ec2fefcde377d124f")
            .method(original.method, original.body)
        return chain.proceed(builder.build())
    }
}