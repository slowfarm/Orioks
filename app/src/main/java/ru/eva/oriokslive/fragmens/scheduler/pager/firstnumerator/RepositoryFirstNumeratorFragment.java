package ru.eva.oriokslive.fragmens.scheduler.pager.firstnumerator;

import java.util.List;

import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.models.schedule.Data;

public class RepositoryFirstNumeratorFragment implements ContractFirstNumeratorFragment.Repository {
    @Override
    public List<Data> getSchedule() {
        return StorageHelper.getInstance().getSchedulersDataCurrentWeek(0);
    }
}
