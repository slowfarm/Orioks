package ru.eva.oriokslive.models.miet.news;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class Channel {

    @ElementList(name = "item", inline = true, required = false)
    private List<Item> itemList;

    public List<Item> getItems() {
        return itemList;
    }
}
