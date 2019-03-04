package ru.eva.oriokslive.activities.schedule.pager.tomorrow;

import java.util.List;

import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.models.miet.schedule.Data;

class RepositoryTomorrowFragment implements ContractTomorrowFragment.Repository {
    @Override
    public List<Data> getSchedule(int week, int day, String group) {
        return LocalStorage.getInstance().getSchedulersDataCurrentDay(week, day, group);
    }
}
