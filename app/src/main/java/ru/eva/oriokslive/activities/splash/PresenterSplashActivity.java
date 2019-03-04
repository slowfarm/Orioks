package ru.eva.oriokslive.activities.splash;

import java.util.List;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnNewsReceived;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.miet.news.News;
import ru.eva.oriokslive.models.miet.news.NewsResponse;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Security;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.miet.schedule.Schedulers;

public class PresenterSplashActivity implements ContractSplashActivity.Presenter,
        OnStudentRecieved,
        OnTokenRecieved,
        OnAllAccessTokensReceived,
        OnNewsReceived {
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
                mRepository.getAllActiveTokens(mRepository.getAccessToken(), this);
            }
        } else {
            mView.showToast("Токен аннулирован");
            mRepository.deleteToken(this);
        }
    }

    @Override
    public void onResponse(List<Security> tokens) {
        mRepository.setAllActiveTokens(tokens);
        mRepository.getNews(this);

    }

    @Override
    public void onResponse(NewsResponse newsResponse) {
        List<News> newsList = ConvertHelper.getInstance().news(newsResponse);
        mRepository.setNews(newsList);
        mView.startMainActivity();
    }

    @Override
    public void onResponse(AccessToken accessToken) {
        finishApp();
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast(t.getMessage());
        mView.startMainActivity();
    }

    private void finishApp(){
        mRepository.clearAllTables();
        mView.startRegistrationActivity();
    }
}
