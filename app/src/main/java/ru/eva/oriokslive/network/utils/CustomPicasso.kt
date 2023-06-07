package ru.eva.oriokslive.network.utils

import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient

fun Picasso.Builder.cookie(cookie: String?): Picasso =
    downloader(OkHttp3Downloader(getDownloader(cookie))).build()

private fun getDownloader(cookie: String?) = OkHttpClient.Builder()
    .addInterceptor(Interceptor { chain ->
        val builder = chain.request().newBuilder()
        cookie?.let { builder.addHeader("Cookie", cookie) }
        chain.proceed(builder.build())
    })
    .build()