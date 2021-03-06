package ru.eva.oriokslive.fragmens.schedule;

import android.app.Activity;
import android.app.Fragment;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.activities.group.GroupActivity;
import ru.eva.oriokslive.activities.main.MainActivity;
import ru.eva.oriokslive.activities.schedule.SchedulerActivity;
import ru.eva.oriokslive.adapters.SchedulerFragmentAdapter;
import ru.eva.oriokslive.interfaces.OnGroupDeleteButtonClickListener;
import ru.eva.oriokslive.interfaces.OnGroupItemClickListener;

public class SchedulerFragment extends Fragment implements ContractSchedulerFragment.View,
        OnGroupItemClickListener,
        OnGroupDeleteButtonClickListener {

    private View view;
    private SchedulerFragmentAdapter adapter;

    private ContractSchedulerFragment.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scheduler, container, false);
        FloatingActionButton add = view.findViewById(R.id.add_btn);
        add.setImageResource(R.drawable.ic_add);
        add.setOnClickListener(v-> startActivityForResult(new Intent(view.getContext(), GroupActivity.class),1));
        mPresenter = new PresenterSchedulerFragment(this);
        mPresenter.getRecyclerView();
        return view;
    }

    @Override
    public void setRecyclerView(List<String> groupList) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new SchedulerFragmentAdapter(groupList);
        adapter.setOnGroupItemClickListener(this);
        adapter.setOnGroupDeleteButtonClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void notifyDataSetChanged(List<String> groupList) {
        adapter.addItems(groupList);
    }

    @Override
    public void onClick(View v, String group) {
        switch (v.getId()) {
            case R.id.front_layout:
                startSchedulerActivity(group);
                break;
            case R.id.add:
                mPresenter.addPinnedShortcuts(v.getContext(), group);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == -1) {
            mPresenter.addLocalGroup(data.getStringExtra("group"));
        }
    }

    @Override
    public void onClick(String group, int position) {
        mPresenter.removeGroup(group);
        adapter.removeItem(position);
    }

    private void startSchedulerActivity(String group) {
        startActivity(new Intent(view.getContext(), SchedulerActivity.class).putExtra("group", group));
    }
}
