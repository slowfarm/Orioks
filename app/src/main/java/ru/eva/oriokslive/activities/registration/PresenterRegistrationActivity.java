package ru.eva.oriokslive.activities.registration;

import android.util.Base64;

import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;

public class PresenterRegistrationActivity implements ContractRegistrationActivity.Presenter, OnTokenRecieved {
    private ContractRegistrationActivity.View mView;
    private ContractRegistrationActivity.Repository mRepository;

    PresenterRegistrationActivity(ContractRegistrationActivity.View mView) {
        this.mView = mView;
        mRepository = new RepositoryRegistrationActivity();
    }

    @Override
    public void checkAccessToken() {
        if(mRepository.getAccessToken() != null) {
           mView.startActivity();
        }
    }

    @Override
    public void onButtonWasClicked(String login, String password) {
        if(login.equals("")) {
            mView.showToast("Пожалуйста, введите логин");
        } else if(password.equals("")) {
            mView.showToast("Пожалуйста, введите пароль");
        } else {
            String encodedString = "Basic "+Base64.encodeToString((login+":"+password).getBytes(), Base64.NO_WRAP);
            mRepository.getAccessToken(encodedString, this);
        }
    }


    @Override
    public void onResponse(AccessToken accessToken) {
        if(accessToken != null) {
            mRepository.setAccessToken(accessToken);
            mView.startActivity();
        } else {
            mView.showToast("Неверный логин либо пароль");
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
    }
}
