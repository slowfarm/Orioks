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

        void unsetRefreshing();

        void notifyItemRemoved(int position);

        void notifyDataSetChanged();
    }

    interface Presenter {

        void deleteActiveToken(Security token, int position);

        void getAllActiveTokens();

        void refreshActiveTokens();
    }

    interface Repository {

        void deleteActiveToken(Security token, OnTokenRecieved onTokenRecieved);

        void getAllActiveTokens(OnAllAccessTokensReceived onAllAccessTokensReceived);

        void setAllActiveTokens(List<Security> tokens);

        void clearAllTables();

        List<Security> getAllActiveLocalTokens();

        void deleteActiveLocalToken(Security token);
    }
}
