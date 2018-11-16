package ru.eva.oriokslive.fragmens.security;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.activities.registration.RegistrationActivity;
import ru.eva.oriokslive.adapters.SecurityAdapter;
import ru.eva.oriokslive.interfaces.OnDeleteButtonClickListener;
import ru.eva.oriokslive.models.orioks.Security;

public class SecurityFragment extends Fragment implements ContractSecurityFragment.View, OnDeleteButtonClickListener {


    private ContractSecurityFragment.Presenter mPresenter;
    private RecyclerView recyclerView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_security, container, false);

        mPresenter = new PresenterSecurityFragment(this);
        recyclerView = view.findViewById(R.id.recycler_view);

        mPresenter.getAllActiveTokens();
        return view;
    }

    @Override
    public void onClick(String token) {
        mPresenter.deleteActiveToken(token);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(List<Security> allActiveTokens) {
        SecurityAdapter adapter = new SecurityAdapter(allActiveTokens);
        adapter.setOnDeleteButtonClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void finishActivity() {
        ((Activity)view.getContext()).finishAffinity();
        view.getContext().startActivity(new Intent(view.getContext(), RegistrationActivity.class));
    }
}