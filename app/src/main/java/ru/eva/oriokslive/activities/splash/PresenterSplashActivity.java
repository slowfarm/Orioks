package ru.eva.oriokslive.activities.splash;

import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.schedule.Schedulers;

public class PresenterSplashActivity implements ContractSplashActivity.Presenter, OnStudentRecieved, OnSchedulersReceived, OnTokenRecieved {
    private ContractSplashActivity.View mView;
    private ContractSplashActivity.Repository mRepository;

    PresenterSplashActivity(ContractSplashActivity.View mView) {
        this.mView = mView;
        mRepository = new RepositorySplashActivity();
    }

    @Override
    public void checkAccessToken() {
        if(mRepository.getAccessToken() != null) {
            mRepository.getStudent(this);

        } else {
            mView.startRegistrationActivity();
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
        } else {
            mView.showToast("Токен аннулирован");
            mRepository.deleteToken(this);
        }
    }

    @Override
    public void onResponse(Schedulers schedulers) {
        mRepository.setSchedule(schedulers);
        mView.startMainActivity();
    }

    @Override
    public void onResponse(AccessToken accessToken) {
        finishApp();
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
        mView.startMainActivity();
    }

    private void finishApp(){
        mRepository.clearAllTables();
        mView.startRegistrationActivity();
    }
}
