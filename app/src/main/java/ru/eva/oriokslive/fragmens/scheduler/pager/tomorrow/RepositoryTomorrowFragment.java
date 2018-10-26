package ru.eva.oriokslive.fragmens.scheduler.pager.tomorrow;

import java.util.List;

import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.models.schedule.Data;

class RepositoryTomorrowFragment implements ContractTomorrowFragment.Repository {
    @Override
    public List<Data> getSchedule(int week, int day) {
        return StorageHelper.getInstance().getSchedulersDataCurrentDay(week, day);
    }
}
