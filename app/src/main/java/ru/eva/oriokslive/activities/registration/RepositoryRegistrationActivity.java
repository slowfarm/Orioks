package ru.eva.oriokslive.activities.registration;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.schedule.Schedulers;

public class RepositoryRegistrationActivity implements ContractRegistrationActivity.Repository {

    @Override
    public void getAccessToken(String encodedString, OnTokenRecieved onTokenRecieved) {
        RetrofitHelper.getInstance().setOnTokenReceived(onTokenRecieved);
        RetrofitHelper.getInstance().getAccessToken(encodedString);
    }

    @Override
    public void setAccessToken(AccessToken accessToken) {
        StorageHelper.getInstance().setAccessToken(accessToken);
    }

    @Override
    public void getStudent(OnStudentRecieved onStudentRecieved) {
        RetrofitHelper.getInstance().setOnStudentReceived(onStudentRecieved);
        RetrofitHelper.getInstance().getStudent(StorageHelper.getInstance().getAccessToken());
    }

    @Override
    public void setStudent(Student student) {
        StorageHelper.getInstance().setStudent(student);
    }

    @Override
    public void setSchedule(Schedulers schedulers) {
        StorageHelper.getInstance().setSchedulers(schedulers);
    }

    @Override
    public void getSchedule(String group, OnSchedulersReceived onSchedulersReceived) {
        RetrofitHelper.getInstance().setOnSchedulersReceived(onSchedulersReceived);
        RetrofitHelper.getInstance().getSchedulers(group);
    }
}
