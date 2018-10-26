package ru.eva.oriokslive.fragmens.scheduler.pager.firstdenomination;

import java.util.List;

import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.models.schedule.Data;

public class RepositoryFirstDenominationFragment implements ContractFirstDenominationFragment.Repository {
    @Override
    public List<Data> getSchedule() {
        return StorageHelper.getInstance().getSchedulersDataCurrentWeek(1);
    }
}
