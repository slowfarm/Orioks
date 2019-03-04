package ru.eva.oriokslive.activities.schedule.pager.tomorrow;

import java.util.List;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.models.miet.schedule.Data;

class PresenterTomorrowFragment implements ContractTomorrowFragment.Presenter{
    private ContractTomorrowFragment.View mView;
    private ContractTomorrowFragment.Repository mRepository;


    PresenterTomorrowFragment(ContractTomorrowFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryTomorrowFragment();
    }

    @Override
    public void getSchedule(String group) {
        int currentDay = ConvertHelper.getInstance().calculateCurrentDay();
        int nextDayOfWeek = ConvertHelper.getInstance().getNextDayOfWeek();
        List<Data> dataList = ConvertHelper.getInstance().scheduleDay(mRepository.getSchedule(currentDay, nextDayOfWeek, group));
        mView.setAdapter(dataList);
    }
}
