package ru.eva.oriokslive.fragmens.Main;

import java.util.List;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.models.orioks.Disciplines;

class Repository implements Contract.Repository {
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
}
