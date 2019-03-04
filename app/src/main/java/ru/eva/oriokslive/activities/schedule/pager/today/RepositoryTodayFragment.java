package ru.eva.oriokslive.activities.schedule.pager.today;

import java.util.List;

import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.models.miet.schedule.Data;

class RepositoryTodayFragment implements ContractTodayFragment.Repository {
    @Override
    public List<Data> getSchedule(int week, int day, String group) {
        return LocalStorage.getInstance().getSchedulersDataCurrentDay(week, day, group);
    }
}
