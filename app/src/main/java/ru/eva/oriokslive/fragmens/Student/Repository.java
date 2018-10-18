package ru.eva.oriokslive.fragmens.Student;

import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.models.orioks.Student;

class Repository implements Contract.Repository {
    @Override
    public Student getStudent() {
        return StorageHelper.getInstance().getStudent();
    }

    @Override
    public void clearAllTables() {
        StorageHelper.getInstance().clearTables();
    }
}
