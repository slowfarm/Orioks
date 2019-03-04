package ru.eva.oriokslive.activities.main;

import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.models.orioks.Student;

class RepositoryMainActivity implements ContractMainActivity.Repository {

    @Override
    public Student getStudent() {
        return LocalStorage.getInstance().getStudent();
    }
}