package ru.eva.oriokslive.interfaces.http;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import ru.eva.oriokslive.models.miet.news.NewsResponse;

public interface NewsAPI {
    @POST("/rss/news")
    Call<NewsResponse> getNews();
}

