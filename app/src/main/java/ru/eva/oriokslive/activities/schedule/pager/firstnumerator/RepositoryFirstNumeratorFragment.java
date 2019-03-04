package ru.eva.oriokslive.activities.schedule.pager.firstnumerator;

import java.util.List;

import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.models.miet.schedule.Data;

public class RepositoryFirstNumeratorFragment implements ContractFirstNumeratorFragment.Repository {
    @Override
    public List<Data> getSchedule(String group) {
        return LocalStorage.getInstance().getSchedulersDataCurrentWeek(0, group);
    }
}
