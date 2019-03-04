package ru.eva.oriokslive.fragmens.news;

import java.util.List;

import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnNewsReceived;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.miet.news.News;
import ru.eva.oriokslive.models.miet.news.NewsResponse;
import ru.eva.oriokslive.models.orioks.Security;

class ContractNewsFragment {
    interface View {

        void showToast(String text);

        void setAdapter(List<News> allActiveTokens);

        void unsetRefreshing();

        void addRecyclerViewItems(List<News> convertTokens);
    }

    interface Presenter {

        void getNews();

        void refreshActiveTokens();
    }

    interface Repository {

        void getNews(OnNewsReceived onNewsReceived);

        List<News> getLocalNews();

        void setNews(List<News> news);
    }
}
