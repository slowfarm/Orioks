package ru.eva.oriokslive.fragmens.Scheduler;

import java.util.List;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.models.schedule.Data;
import ru.eva.oriokslive.models.schedule.Schedulers;

class Repository implements Contract.Repository {
    @Override
    public List<Data> getSchedulersDataCurrentWeek(int currentWeek) {
        return StorageHelper.getInstance().getSchedulersDataCurrentWeek(currentWeek);
    }

    @Override
    public void getSchedule(OnSchedulersReceived onSchedulersReceived) {
        RetrofitHelper.getInstance().setOnSchedulersReceived(onSchedulersReceived);
        RetrofitHelper.getInstance().getSchedulers(StorageHelper.getInstance().getStudent().getGroup());
    }

    @Override
    public void setSchedule(Schedulers schedulers) {
        StorageHelper.getInstance().setSchedulers(schedulers);
    }
}
