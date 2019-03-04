package ru.eva.oriokslive.interfaces;

import ru.eva.oriokslive.models.miet.news.NewsResponse;

public interface OnNewsReceived {
    void onResponse(NewsResponse newsResponse);

    void onFailure(Throwable t);
}
