package ru.eva.oriokslive.activities.schedule.pager.seconddenominator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.adapters.SchedulerAdapter;
import ru.eva.oriokslive.models.miet.schedule.Data;

public class SecondDenominatorFragment extends Fragment implements ContractSecondDenominationFragment.View {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewItem = inflater.inflate(R.layout.scheduler_pager_item, container, false);

        String group = getArguments().getString("group","");

        recyclerView = viewItem.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(viewItem.getContext()));

        ContractSecondDenominationFragment.Presenter mPresenter = new PresenterSecondDenominationFragment(this);
        mPresenter.getSchedule(group);

        return viewItem;
    }

    @Override
    public void setAdapter(List<Data> schedule) {
        SchedulerAdapter adapter = new SchedulerAdapter(schedule);
        recyclerView.setAdapter(adapter);
    }
}