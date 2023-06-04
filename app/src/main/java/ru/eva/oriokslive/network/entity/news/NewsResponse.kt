package ru.eva.oriokslive.network.entity.news

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class NewsResponse(
    @field:Element(name = "channel")
    @param:Element(name = "channel")
    val channel: Channel,
)