package ru.eva.oriokslive.network

import retrofit2.http.POST

interface CookieApi {
    @POST("/rss")
    suspend fun getCookie(): String
}