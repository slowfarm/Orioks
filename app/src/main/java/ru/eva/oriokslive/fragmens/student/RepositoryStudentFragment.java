package ru.eva.oriokslive.fragmens.student;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.Student;

class RepositoryStudentFragment implements ContractStudentFragment.Repository {
    @Override
    public Student getStudent() {
        return StorageHelper.getInstance().getStudent();
    }

    @Override
    public void clearAllTables() {
        StorageHelper.getInstance().clearTables();
    }

    @Override
    public void deleteToken(OnTokenRecieved onTokenRecieved) {
        RetrofitHelper.getInstance().setOnTokenReceived(onTokenRecieved);
        RetrofitHelper.getInstance().deleteAccessToken(StorageHelper.getInstance().getAccessToken());
    }
}
