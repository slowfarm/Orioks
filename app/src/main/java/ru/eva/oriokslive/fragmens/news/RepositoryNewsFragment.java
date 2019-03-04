package ru.eva.oriokslive.fragmens.news;

import java.util.List;

import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.domain.NetworkStorage;
import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnNewsReceived;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.miet.news.News;
import ru.eva.oriokslive.models.miet.news.NewsResponse;
import ru.eva.oriokslive.models.orioks.Security;

class RepositoryNewsFragment implements ContractNewsFragment.Repository {


    @Override
    public void getNews(OnNewsReceived onNewsReceived) {
        NetworkStorage.getInstance().setOnNewsReceived(onNewsReceived);
        NetworkStorage.getInstance().getNews();
    }

    @Override
    public List<News> getLocalNews() {
        return LocalStorage.getInstance().getNews();
    }

    @Override
    public void setNews(List<News> news) {
        LocalStorage.getInstance().setNews(news);
    }
}
