package ru.eva.oriokslive.fragmens.news;

import android.util.Log;

import java.util.List;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.interfaces.OnNewsReceived;
import ru.eva.oriokslive.models.miet.news.News;
import ru.eva.oriokslive.models.miet.news.NewsResponse;

class PresenterNewsFragment implements ContractNewsFragment.Presenter, OnNewsReceived {
    private ContractNewsFragment.View mView;
    private ContractNewsFragment.Repository mRepository;

    PresenterNewsFragment(ContractNewsFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryNewsFragment();
    }

    @Override
    public void getNews() {
        mView.setAdapter(mRepository.getLocalNews());
    }

    @Override
    public void refreshActiveTokens() {
        mRepository.getNews(this);
    }

    @Override
    public void onResponse(NewsResponse newsResponse) {
        mView.unsetRefreshing();
        if(newsResponse.getChannel() != null) {
            List<News> newsList = ConvertHelper.getInstance().news(newsResponse);
            mRepository.setNews(newsList);
            mView.addRecyclerViewItems(newsList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
        Log.e("XML", t.getMessage());
        mView.unsetRefreshing();
    }
}
