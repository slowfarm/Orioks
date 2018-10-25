package ru.eva.oriokslive.fragmens.Main;

import java.util.List;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.Disciplines;

class RepositoryMainFragment implements ContractMainFragment.Repository {
    @Override
    public List<Disciplines> getDisciplineList() {
        return StorageHelper.getInstance().getDisciplines();
    }

    @Override
    public void setDisciplineList(OnDisciplinesRecieved onDisciplinesRecieved) {
        RetrofitHelper.getInstance().setOnDisciplinesReceived(onDisciplinesRecieved);
        RetrofitHelper.getInstance().getDisciplines(StorageHelper.getInstance().getAccessToken(), "", "");
    }

    @Override
    public void setDisciplineList(List<Disciplines> disciplinesList) {
        StorageHelper.getInstance().setDisciplines(disciplinesList);
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
