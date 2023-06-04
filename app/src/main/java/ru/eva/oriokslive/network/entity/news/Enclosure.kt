package ru.eva.oriokslive.network.entity.news

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "enclosure", strict = false)
data class Enclosure(
    @field:Attribute(name = "url")
    @param:Attribute(name = "url")
    val url: String,
)