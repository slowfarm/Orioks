package ru.eva.oriokslive.fragmens.news;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.activities.news.NewsActivity;
import ru.eva.oriokslive.adapters.NewsFragmentAdapter;
import ru.eva.oriokslive.interfaces.OnItemClickListener;
import ru.eva.oriokslive.models.miet.news.News;

public class NewsFragment extends Fragment implements
        ContractNewsFragment.View,
        OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {


    private ContractNewsFragment.Presenter mPresenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsFragmentAdapter adapter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);

        Resources resources = getResources();
        int orange = resources.getColor(R.color.md_orange_A700);
        int yellow = resources.getColor(R.color.md_yellow_A700);
        int lightGreen = resources.getColor(R.color.md_light_green_A700);
        int green = resources.getColor(R.color.md_green_A700);

        mPresenter = new PresenterNewsFragment(this);
        recyclerView = view.findViewById(R.id.recycler_view);

        swipeRefreshLayout = view.findViewById(R.id.container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(orange, yellow, lightGreen, green);

        mPresenter.getNews();
        return view;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(List<News> news) {
        adapter = new NewsFragmentAdapter(news);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void unsetRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addRecyclerViewItems(List<News> news) {
        adapter.addItems(news);
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshActiveTokens();
    }

    @Override
    public void onClick(String url, int position) {
        startActivity(new Intent(view.getContext(), NewsActivity.class).putExtra("url", url));
    }
}