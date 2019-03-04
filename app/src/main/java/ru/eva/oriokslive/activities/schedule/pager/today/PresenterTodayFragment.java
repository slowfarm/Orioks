package ru.eva.oriokslive.activities.schedule.pager.today;

import java.util.List;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.models.miet.schedule.Data;

class PresenterTodayFragment implements ContractTodayFragment.Presenter{
    private ContractTodayFragment.View mView;
    private ContractTodayFragment.Repository mRepository;


    PresenterTodayFragment(ContractTodayFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryTodayFragment();
    }

    @Override
    public void getSchedule(String group) {
        int currentDay = ConvertHelper.getInstance().calculateCurrentDay();
        int currentDayOfWeek = ConvertHelper.getInstance().getDayOfWeek();
        List<Data> dataList = ConvertHelper.getInstance().scheduleDay(mRepository.getSchedule(currentDay, currentDayOfWeek, group));
        mView.setAdapter(dataList);
    }


}
