package ru.eva.oriokslive.activities.registration;

import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;

class ContractRegistrationActivity {
    interface View {
        void startActivity();

        void showToast(String text);
    }

    interface Presenter {
        void checkAccessToken();

        void onButtonWasClicked(String login, String password);
    }

    interface Repository {
        String getAccessToken();

        void getAccessToken(String encodedString, OnTokenRecieved onTokenRecieved);

        void setAccessToken(AccessToken token);
    }
}
