package ru.eva.oriokslive.fragmens.security;

import java.util.List;

import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Security;

class PresenterSecurityFragment implements ContractSecurityFragment.Presenter, OnTokenRecieved, OnAllAccessTokensReceived {
    private ContractSecurityFragment.View mView;
    private ContractSecurityFragment.Repository mRepository;
    private int position = 0;
    private Security token;

    PresenterSecurityFragment(ContractSecurityFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositorySecurityFragment();
    }

    @Override
    public void deleteActiveToken(Security token, int position) {
        this.position = position;
        this.token = token;
        mRepository.deleteActiveToken(token, this);
    }

    @Override
    public void getAllActiveTokens() {
        mView.setAdapter(mRepository.getAllActiveLocalTokens());
    }

    @Override
    public void refreshActiveTokens() {
        mRepository.getAllActiveTokens(this);
    }

    @Override
    public void onResponse(AccessToken accessToken) {
        mRepository.deleteActiveLocalToken(token);
        mView.notifyItemRemoved(position);
        mView.showToast("Токен успешно удален");
        mRepository.getAllActiveTokens(this);
    }

    @Override
    public void onResponse(List<Security> tokens) {
        mView.unsetRefreshing();
        if(tokens != null) {
            mRepository.setAllActiveTokens(tokens);
            mView.notifyDataSetChanged();
        }
        else {
            mView.showToast("Токен анулирован");
            finishApp();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
        mView.unsetRefreshing();
    }

    private void finishApp(){
        mRepository.clearAllTables();
        mView.finishActivity();
    }
}
