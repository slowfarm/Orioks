package ru.eva.oriokslive.activities.Registration;

import android.content.Context;

import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;

class Contract {
    interface View {
        void startActivity();

        void showToast(String text);
    }

    interface Presenter {
        void checkAccessToken();

        void onButtonWasClicked(String login, String password, Context context);
    }

    interface Repository {
        String getAccessToken();

        void getAccessToken(String encodedString, OnTokenRecieved onTokenRecieved);

        void setAccessToken(AccessToken token);
    }
}
