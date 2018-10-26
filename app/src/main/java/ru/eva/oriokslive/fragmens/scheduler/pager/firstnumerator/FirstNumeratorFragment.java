package ru.eva.oriokslive.fragmens.scheduler.pager.firstnumerator;

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
import ru.eva.oriokslive.adapters.SchedulerFragmentAdapter;
import ru.eva.oriokslive.models.schedule.Data;

public class FirstNumeratorFragment extends Fragment implements ContractFirstNumeratorFragment.View {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewItem = inflater.inflate(R.layout.scheduler_pager_item, container, false);
        recyclerView = viewItem.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(viewItem.getContext()));

        ContractFirstNumeratorFragment.Presenter mPresenter = new PresenterFirstNumeratorFragment(this);
        mPresenter.getSchedule();

        return viewItem;
    }

    @Override
    public void setAdapter(List<Data> schedule) {
        SchedulerFragmentAdapter adapter = new SchedulerFragmentAdapter(schedule);
        recyclerView.setAdapter(adapter);
    }

}
