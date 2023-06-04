package ru.eva.oriokslive.network.entity.news

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel(
    @ElementList(name = "item", inline = true, required = false)
    val items: List<Item>,
)