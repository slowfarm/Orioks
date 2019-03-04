package ru.eva.oriokslive.activities.schedule.pager.secondnumerator;

import java.util.List;

import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.models.miet.schedule.Data;

public class RepositorySecondNumeratorFragment implements ContractSecondNumeratorFragment.Repository {
    @Override
    public List<Data> getSchedule(String group) {
        return LocalStorage.getInstance().getSchedulersDataCurrentWeek(2, group);
    }
}
