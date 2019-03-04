package ru.eva.oriokslive.activities.schedule.pager.firstdenomination;

import java.util.List;

import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.models.miet.schedule.Data;

public class RepositoryFirstDenominationFragment implements ContractFirstDenominationFragment.Repository {
    @Override
    public List<Data> getSchedule(String group) {
        return LocalStorage.getInstance().getSchedulersDataCurrentWeek(1, group);
    }
}
