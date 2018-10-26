package ru.eva.oriokslive.fragmens.scheduler.pager.secondnumerator;

import java.util.List;

import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.models.schedule.Data;

public class RepositorySecondNumeratorFragment implements ContractSecondNumeratorFragment.Repository {
    @Override
    public List<Data> getSchedule() {
        return StorageHelper.getInstance().getSchedulersDataCurrentWeek(2);
    }
}
