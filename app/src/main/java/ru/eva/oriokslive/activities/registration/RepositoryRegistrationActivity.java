package ru.eva.oriokslive.activities.registration;

import java.util.List;

import ru.eva.oriokslive.domain.NetworkStorage;
import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Security;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.miet.schedule.Schedulers;

public class RepositoryRegistrationActivity implements ContractRegistrationActivity.Repository {

    @Override
    public void getAccessToken(String encodedString, OnTokenRecieved onTokenRecieved) {
        NetworkStorage.getInstance().setOnTokenReceived(onTokenRecieved);
        NetworkStorage.getInstance().getAccessToken(encodedString);
    }

    @Override
    public void setAccessToken(AccessToken accessToken) {
        LocalStorage.getInstance().setAccessToken(accessToken);
    }

    @Override
    public void getStudent(OnStudentRecieved onStudentRecieved) {
        NetworkStorage.getInstance().setOnStudentReceived(onStudentRecieved);
        NetworkStorage.getInstance().getStudent(LocalStorage.getInstance().getAccessToken());
    }

    @Override
    public void setStudent(Student student) {
        LocalStorage.getInstance().setStudent(student);
    }

    @Override
    public void setSchedule(Schedulers schedulers) {
        LocalStorage.getInstance().setSchedulers(schedulers);
    }

    @Override
    public void getSchedule(String group, OnSchedulersReceived onSchedulersReceived) {
        NetworkStorage.getInstance().setOnSchedulersReceived(onSchedulersReceived);
        NetworkStorage.getInstance().getSchedulers(group);
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
}
