package ru.eva.oriokslive.models.miet.news;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Item {

    @Element(name = "title")
    private String title;
    @Element(name = "link")
    private String link;
    @Element(name = "description")
    private String description;
    @Element(name = "enclosure")
    private Enclosure enclosure;
    @Element(name = "pubDate")
    private String date;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }
}
