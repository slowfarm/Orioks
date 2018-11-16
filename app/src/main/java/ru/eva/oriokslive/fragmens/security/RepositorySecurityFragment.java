package ru.eva.oriokslive.fragmens.security;

import java.util.List;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.Disciplines;
import ru.eva.oriokslive.models.orioks.Security;

class RepositorySecurityFragment implements ContractSecurityFragment.Repository {

    @Override
    public void deleteActiveToken(String token, OnTokenRecieved onTokenRecieved) {
        RetrofitHelper.getInstance().setOnTokenReceived(onTokenRecieved);
        RetrofitHelper.getInstance().deleteAccessToken(token);
    }

    @Override
    public void getAllActiveTokens(OnAllAccessTokensReceived onAllAccessTokensReceived) {
        RetrofitHelper.getInstance().setOnAllActiveTokensReceived(onAllAccessTokensReceived);
        RetrofitHelper.getInstance().getAllActiveTokens(StorageHelper.getInstance().getAccessToken());
    }

    @Override
    public void setAllActiveTokens(List<Security> tokens) {
        StorageHelper.getInstance().setAllActiveTokens(tokens);
    }

    @Override
    public void clearAllTables() {
        StorageHelper.getInstance().clearTables();
    }
}
