package ru.eva.oriokslive.fragmens;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.adapters.DisciplineAdapter;
import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Disciplines;

public class MainFragment extends Fragment implements OnDisciplinesRecieved, SwipeRefreshLayout.OnRefreshListener, OnTokenRecieved {

    RecyclerView recyclerView;
    DisciplineAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    List<Disciplines> disciplinesList;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        swipeRefreshLayout = view.findViewById(R.id.container);
        swipeRefreshLayout.setOnRefreshListener(this);

        disciplinesList = StorageHelper.getInstance().getDisciplines();

        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new DisciplineAdapter(disciplinesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        RetrofitHelper.getInstance().setOnTokenReceived(this);
        RetrofitHelper.getInstance().setOnDisciplinesReceived(this);
        RetrofitHelper.getInstance().getDisciplines(StorageHelper.getInstance().getAccessToken(view.getContext()), "", "");

        return view;
    }

    @Override
    public void onResponse(List<Disciplines> disciplinesList) {
        if(disciplinesList.size() == 0)
            RetrofitHelper.getInstance().getAccessToken(StorageHelper.getInstance().getLoginAndPassword(view.getContext()));
        StorageHelper.getInstance().setDisciplines(disciplinesList);
        adapter.addItems(disciplinesList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResponse(AccessToken accessToken) {
        Toast.makeText(view.getContext(), "Токен успешно получен", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
        Log.d("LogD", t.getMessage());
    }

    @Override
    public void onRefresh() {
        RetrofitHelper.getInstance().getDisciplines(StorageHelper.getInstance().getAccessToken(view.getContext()), "", "");
    }
}