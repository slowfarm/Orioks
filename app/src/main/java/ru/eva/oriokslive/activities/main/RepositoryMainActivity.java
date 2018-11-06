package ru.eva.oriokslive.activities.main;

import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.models.orioks.Student;

class RepositoryMainActivity implements ContractMainActivity.Repository {

    @Override
    public Student getStudent() {
        return StorageHelper.getInstance().getStudent();
    }
}