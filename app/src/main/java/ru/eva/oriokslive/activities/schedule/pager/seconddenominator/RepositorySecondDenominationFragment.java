package ru.eva.oriokslive.activities.schedule.pager.seconddenominator;

import java.util.List;

import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.models.miet.schedule.Data;

public class RepositorySecondDenominationFragment implements ContractSecondDenominationFragment.Repository {
    @Override
    public List<Data> getSchedule(String group) {
        return LocalStorage.getInstance().getSchedulersDataCurrentWeek(3, group);
    }
}
