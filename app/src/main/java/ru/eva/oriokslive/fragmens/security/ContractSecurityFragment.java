package ru.eva.oriokslive.fragmens.security;

import java.util.List;

import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.Security;

class ContractSecurityFragment {
    interface View {

        void showToast(String text);

        void setAdapter(List<Security> allActiveTokens);

        void finishActivity();
    }

    interface Presenter {

        void deleteActiveToken(String token);
        void getAllActiveTokens();
    }

    interface Repository {

        void deleteActiveToken(String token, OnTokenRecieved onTokenRecieved);

        void getAllActiveTokens(OnAllAccessTokensReceived onAllAccessTokensReceived);

        void setAllActiveTokens(List<Security> tokens);

        void clearAllTables();
    }
}
