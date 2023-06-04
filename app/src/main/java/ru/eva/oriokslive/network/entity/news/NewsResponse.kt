package ru.eva.oriokslive.network.entity.news

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class NewsResponse(
    @Element(name = "channel")
    val channel: Channel,
)