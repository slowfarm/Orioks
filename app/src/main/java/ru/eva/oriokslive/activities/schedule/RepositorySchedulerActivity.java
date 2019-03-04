package ru.eva.oriokslive.activities.schedule;

import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.domain.NetworkStorage;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.models.miet.schedule.Schedulers;

class RepositorySchedulerActivity implements ContractSchedulerActivity.Repository {

    @Override
    public void getSchedule(OnSchedulersReceived onSchedulersReceived, String group) {
        NetworkStorage.getInstance().setOnSchedulersReceived(onSchedulersReceived);
        NetworkStorage.getInstance().getSchedulers(group);
    }

    @Override
    public void setSchedule(Schedulers schedulers) {
        LocalStorage.getInstance().setSchedulers(schedulers);
    }

    @Override
    public Schedulers getLocalSchedule(String group) {
        return LocalStorage.getInstance().getSchedule(group);
    }
}
