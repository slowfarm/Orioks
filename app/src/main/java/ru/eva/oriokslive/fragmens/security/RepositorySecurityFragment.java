package ru.eva.oriokslive.fragmens.security;

import java.util.List;

import ru.eva.oriokslive.domain.NetworkStorage;
import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.Security;

class RepositorySecurityFragment implements ContractSecurityFragment.Repository {

    @Override
    public void deleteActiveToken(Security token, OnTokenRecieved onTokenRecieved) {
        NetworkStorage.getInstance().setOnTokenReceived(onTokenRecieved);
        NetworkStorage.getInstance().deleteAccessToken(token.getToken());
    }

    @Override
    public void getAllActiveTokens(OnAllAccessTokensReceived onAllAccessTokensReceived) {
        NetworkStorage.getInstance().setOnAllActiveTokensReceived(onAllAccessTokensReceived);
        NetworkStorage.getInstance().getAllActiveTokens(LocalStorage.getInstance().getAccessToken());
    }

    @Override
    public void setAllActiveTokens(List<Security> tokens) {
        LocalStorage.getInstance().setAllActiveTokens(tokens);
    }

    @Override
    public void clearAllTables() {
        LocalStorage.getInstance().clearTables();
    }

    @Override
    public List<Security> getAllActiveLocalTokens() {
        return LocalStorage.getInstance().getAllActiveTokens();
    }

    @Override
    public void deleteActiveLocalToken(Security token) {
        LocalStorage.getInstance().deleteActiveToken(token);
    }
}
