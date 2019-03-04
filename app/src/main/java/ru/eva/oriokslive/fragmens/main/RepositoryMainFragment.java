package ru.eva.oriokslive.fragmens.main;

import java.util.List;

import ru.eva.oriokslive.domain.NetworkStorage;
import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.Discipline;

class RepositoryMainFragment implements ContractMainFragment.Repository {
    @Override
    public List<Discipline> getDisciplineList() {
        return LocalStorage.getInstance().getDisciplines();
    }

    @Override
    public void setDisciplineList(OnDisciplinesRecieved onDisciplinesRecieved) {
        NetworkStorage.getInstance().setOnDisciplinesReceived(onDisciplinesRecieved);
        NetworkStorage.getInstance().getDisciplines(LocalStorage.getInstance().getAccessToken(), "", "");
    }

    @Override
    public void setDisciplineList(List<Discipline> disciplineList) {
        LocalStorage.getInstance().setDisciplines(disciplineList);
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
