package ru.eva.oriokslive.fragmens.security;

import java.util.List;

import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Security;

class PresenterSecurityFragment implements ContractSecurityFragment.Presenter, OnTokenRecieved, OnAllAccessTokensReceived {
    private ContractSecurityFragment.View mView;
    private ContractSecurityFragment.Repository mRepository;

    PresenterSecurityFragment(ContractSecurityFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositorySecurityFragment();
    }

    @Override
    public void deleteActiveToken(String token) {
        mRepository.deleteActiveToken(token, this);
    }

    @Override
    public void getAllActiveTokens() {
        mView.setAdapter(StorageHelper.getInstance().getAllActiveTokens());
    }

    @Override
    public void onResponse(AccessToken accessToken) {
        mView.showToast("Токен успешно удален");
        mRepository.getAllActiveTokens(this);
    }

    @Override
    public void onResponse(List<Security> tokens) {
        if(tokens != null) {
            mRepository.setAllActiveTokens(tokens);
        }
        else {
            finishApp();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
    }

    private void finishApp(){
        mRepository.clearAllTables();
        mView.finishActivity();
    }
}
