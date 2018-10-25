package ru.eva.oriokslive.fragmens.Main;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
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
import ru.eva.oriokslive.activities.Registration.RegistrationActivity;
import ru.eva.oriokslive.adapters.DisciplineAdapter;
import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.models.orioks.Disciplines;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ContractMainFragment.View {

    private DisciplineAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        swipeRefreshLayout = view.findViewById(R.id.container);
        swipeRefreshLayout.setOnRefreshListener(this);

        ContractMainFragment.Presenter mPresenter = new PresenterMainFragment(this);
        mPresenter.getDisciplineList();
        mPresenter.setDisciplineList();

        return view;
    }

    @Override
    public void onRefresh() {
        RetrofitHelper.getInstance().getDisciplines(StorageHelper.getInstance().getAccessToken(), "", "");
    }

    @Override
    public void setRecyclerView(List<Disciplines> disciplineList) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new DisciplineAdapter(disciplineList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addRecyclerVIewItems(List<Disciplines> disciplinesList) {
        adapter.addItems(disciplinesList);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void unsetRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void finishActivity() {
        ((Activity)view.getContext()).finishAffinity();
        view.getContext().startActivity(new Intent(view.getContext(), RegistrationActivity.class));
    }
}