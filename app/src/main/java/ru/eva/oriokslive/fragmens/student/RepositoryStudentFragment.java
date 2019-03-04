package ru.eva.oriokslive.fragmens.student;

import ru.eva.oriokslive.domain.NetworkStorage;
import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.Student;

class RepositoryStudentFragment implements ContractStudentFragment.Repository {
    @Override
    public Student getStudent() {
        return LocalStorage.getInstance().getStudent();
    }

    @Override
    public void clearAllTables() {
        LocalStorage.getInstance().clearTables();
    }

    @Override
    public void deleteToken(OnTokenRecieved onTokenRecieved) {
        NetworkStorage.getInstance().setOnTokenReceived(onTokenRecieved);
        NetworkStorage.getInstance().deleteAccessToken(LocalStorage.getInstance().getAccessToken());
    }
}
