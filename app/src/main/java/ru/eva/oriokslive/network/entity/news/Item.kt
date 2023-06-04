package ru.eva.oriokslive.network.entity.news

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Item(
    @Element(name = "title")
    val title: String,
    @Element(name = "link")
    val link: String,
    @Element(name = "description")
    val description: String,
    @Element(name = "enclosure")
    val enclosure: Enclosure,
    @Element(name = "pubDate")
    val date: String,
)