package ru.eva.oriokslive.fragmens.security;

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
import ru.eva.oriokslive.activities.registration.RegistrationActivity;
import ru.eva.oriokslive.adapters.SecurityFragmentAdapter;
import ru.eva.oriokslive.interfaces.OnDeleteButtonClickListener;
import ru.eva.oriokslive.models.orioks.Security;

public class SecurityFragment extends Fragment implements
        ContractSecurityFragment.View,
        OnDeleteButtonClickListener,
        SwipeRefreshLayout.OnRefreshListener {


    private ContractSecurityFragment.Presenter mPresenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SecurityFragmentAdapter adapter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_security, container, false);

        mPresenter = new PresenterSecurityFragment(this);
        recyclerView = view.findViewById(R.id.recycler_view);

        swipeRefreshLayout = view.findViewById(R.id.container);
        swipeRefreshLayout.setOnRefreshListener(this);

        mPresenter.getAllActiveTokens();
        return view;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(List<Security> allActiveTokens) {
        adapter = new SecurityFragmentAdapter(allActiveTokens);
        adapter.setOnDeleteButtonClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(Security token, int position) {
        mPresenter.deleteActiveToken(token, position);
    }

    @Override
    public void finishActivity() {
        ((Activity)view.getContext()).finishAffinity();
        view.getContext().startActivity(new Intent(view.getContext(), RegistrationActivity.class));
    }

    @Override
    public void unsetRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void notifyItemRemoved(int position) {
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onRefresh() {
        mPresenter.refreshActiveTokens();
    }
}