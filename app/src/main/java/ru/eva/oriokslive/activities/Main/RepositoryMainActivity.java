package ru.eva.oriokslive.activities.Main;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.models.orioks.Student;

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
}