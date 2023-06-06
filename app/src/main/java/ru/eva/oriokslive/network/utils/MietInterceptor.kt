package ru.eva.oriokslive.network.utils

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MietInterceptor(private val cookieManager: CookieManager) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        Timber.e("cookies ${original.headers("Set-Cookie")}")
        for (cookie in cookieManager.cookieStore.cookies) {
            val expiration = Date(System.currentTimeMillis() + 60 * 60 * 1000)
            val pattern = "EEE, dd MMM yyyy HH:mm:ss zzz"
            val expires = SimpleDateFormat(pattern, Locale.getDefault()).format(expiration)
            val cookieValue =
                "${cookie.name}=${cookie.value} ; path=${cookie.path}; domain=${cookie.domain}; expires=$expires"
            builder.addHeader("Cookie", cookieValue)
        }
        builder.method(original.method, original.body)
        return chain.proceed(builder.build())
    }
}