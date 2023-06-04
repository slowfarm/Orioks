package ru.eva.oriokslive.network

import retrofit2.http.POST
import ru.eva.oriokslive.network.entity.news.NewsResponse


interface NewsApi {
    @POST("/rss/news")
    fun getNews(): NewsResponse?
}