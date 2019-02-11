package ru.eva.oriokslive.activities.registration;

import android.util.Base64;

import java.util.List;

import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Security;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.schedule.Schedulers;

public class PresenterRegistrationActivity implements ContractRegistrationActivity.Presenter,
        OnTokenRecieved,OnStudentRecieved, OnSchedulersReceived, OnAllAccessTokensReceived {
    private ContractRegistrationActivity.View mView;
    private ContractRegistrationActivity.Repository mRepository;

    PresenterRegistrationActivity(ContractRegistrationActivity.View mView) {
        this.mView = mView;
        mRepository = new RepositoryRegistrationActivity();
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
            mRepository.getStudent(this);
        } else {
            mView.showToast("Неверный логин либо пароль");
            mView.setButtonEnabled();
        }
    }

    @Override
    public void onResponse(Student student) {
        if(student != null) {
            if(student.getError() != null) {
                mView.showToast(student.getError());
            }
            else {
                mRepository.setStudent(student);
                mRepository.getSchedule(student.getGroup(), this);
            }
        }
    }

    @Override
    public void onResponse(Schedulers schedulers) {
        mRepository.setSchedule(schedulers);
        mRepository.getAllActiveTokens(this);
    }

    @Override
    public void onResponse(List<Security> tokens) {
        mRepository.setAllActiveTokens(tokens);
        mView.startActivity();
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
        mView.setButtonEnabled();
    }
}
