package ru.eva.oriokslive.fragmens.scheduler.pager.seconddenominator;

import java.util.List;

import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.models.schedule.Data;

public class RepositorySecondDenominationFragment implements ContractSecondDenominationFragment.Repository {
    @Override
    public List<Data> getSchedule() {
        return StorageHelper.getInstance().getSchedulersDataCurrentWeek(3);
    }
}
