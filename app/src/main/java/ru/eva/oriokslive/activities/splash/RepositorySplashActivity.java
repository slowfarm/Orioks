package ru.eva.oriokslive.activities.splash;

import java.util.List;

import ru.eva.oriokslive.domain.NetworkStorage;
import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnNewsReceived;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.miet.news.News;
import ru.eva.oriokslive.models.orioks.Security;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.miet.schedule.Schedulers;

public class RepositorySplashActivity implements ContractSplashActivity.Repository {

    @Override
    public String getAccessToken() {
        return LocalStorage.getInstance().getAccessToken();
    }

    @Override
    public void getStudent(OnStudentRecieved onStudentRecieved) {
        NetworkStorage.getInstance().setOnStudentReceived(onStudentRecieved);
        NetworkStorage.getInstance().getStudent(LocalStorage.getInstance().getAccessToken());
    }

    @Override
    public void getSchedule(String group, OnSchedulersReceived onSchedulersReceived) {
        NetworkStorage.getInstance().setOnSchedulersReceived(onSchedulersReceived);
        NetworkStorage.getInstance().getSchedulers(group);
    }

    @Override
    public void setSchedule(Schedulers schedulers) {
        LocalStorage.getInstance().setSchedulers(schedulers);
    }

    @Override
    public void setStudent(Student student) {
        LocalStorage.getInstance().setStudent(student);
    }

    @Override
    public void deleteToken(OnTokenRecieved onTokenRecieved) {
        NetworkStorage.getInstance().setOnTokenReceived(onTokenRecieved);
        NetworkStorage.getInstance().deleteAccessToken(LocalStorage.getInstance().getAccessToken());
    }

    @Override
    public void clearAllTables() {
        LocalStorage.getInstance().clearTables();
    }

    @Override
    public void getAllActiveTokens(String token, OnAllAccessTokensReceived onAllAccessTokensReceived) {
        NetworkStorage.getInstance().setOnAllActiveTokensReceived(onAllAccessTokensReceived);
        NetworkStorage.getInstance().getAllActiveTokens(token);
    }

    @Override
    public void setAllActiveTokens(List<Security> tokens) {
        LocalStorage.getInstance().setAllActiveTokens(tokens);
    }

    @Override
    public void getNews(OnNewsReceived onNewsReceived) {
        NetworkStorage.getInstance().setOnNewsReceived(onNewsReceived);
        NetworkStorage.getInstance().getNews();
    }

    @Override
    public void setNews(List<News> newsList) {
        LocalStorage.getInstance().setNews(newsList);
    }
}
