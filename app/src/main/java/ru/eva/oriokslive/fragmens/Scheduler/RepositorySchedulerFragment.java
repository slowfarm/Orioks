package ru.eva.oriokslive.fragmens.Scheduler;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.models.schedule.Schedulers;

class RepositorySchedulerFragment implements ContractSchedulerFragment.Repository {

    @Override
    public void getSchedule(OnSchedulersReceived onSchedulersReceived) {
        RetrofitHelper.getInstance().setOnSchedulersReceived(onSchedulersReceived);
        RetrofitHelper.getInstance().getSchedulers(StorageHelper.getInstance().getStudent().getGroup());
    }

    @Override
    public void setSchedule(Schedulers schedulers) {
        StorageHelper.getInstance().setSchedulers(schedulers);
    }

    @Override
    public Schedulers getLocalSchedule() {
        return StorageHelper.getInstance().getSchedule();
    }
}
