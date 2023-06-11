package ru.eva.oriokslive.utils.mapper

import ru.eva.oriokslive.network.entity.news.NewsResponse
import ru.eva.oriokslive.ui.entity.NewsItem
import ru.eva.oriokslive.utils.newsDateParser

fun mapNews(response: NewsResponse, cookie: String?): List<NewsItem> = response.channel.items.map {
    NewsItem(
        title = it.title,
        link = it.link,
        description = it.description,
        imageUrl = it.enclosure.url,
        date = newsDateParser(it.date),
        cookie = cookie,
    )
}
