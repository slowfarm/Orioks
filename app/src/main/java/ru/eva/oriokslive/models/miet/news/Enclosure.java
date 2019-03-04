package ru.eva.oriokslive.models.miet.news;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "enclosure", strict = false)
public class Enclosure {

    @Attribute(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }
}