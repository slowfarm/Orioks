package ru.eva.oriokslive.activities.Main;

import android.content.Context;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.models.orioks.Student;

class Repository implements Contract.Repository {


    @Override
    public Student getStudent() {
        return StorageHelper.getInstance().getStudent();
    }

    @Override
    public void updateStudent(Context context, OnStudentRecieved onStudentRecieved) {
        RetrofitHelper.getInstance().setOnStudentReceived(onStudentRecieved);
        RetrofitHelper.getInstance().getStudent(StorageHelper.getInstance().getAccessToken());
    }

    @Override
    public void setStudent(Student student) {
        StorageHelper.getInstance().setStudent(student);
    }
}