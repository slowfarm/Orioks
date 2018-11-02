package ru.eva.oriokslive.activities.main;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.schedule.Schedulers;

class RepositoryMainActivity implements ContractMainActivity.Repository {


    @Override
    public Student getStudent() {
        return StorageHelper.getInstance().getStudent();
    }

    @Override
    public void updateStudent(OnStudentRecieved onStudentRecieved) {
        RetrofitHelper.getInstance().setOnStudentReceived(onStudentRecieved);
        RetrofitHelper.getInstance().getStudent(StorageHelper.getInstance().getAccessToken());
    }

    @Override
    public void setStudent(Student student) {
        StorageHelper.getInstance().setStudent(student);
    }

    @Override
    public void getSchedule(String group, OnSchedulersReceived onSchedulersReceived) {
        RetrofitHelper.getInstance().setOnSchedulersReceived(onSchedulersReceived);
        RetrofitHelper.getInstance().getSchedulers(group);
    }

    @Override
    public void setSchedule(Schedulers schedulers) {
        StorageHelper.getInstance().setSchedulers(schedulers);
    }
}