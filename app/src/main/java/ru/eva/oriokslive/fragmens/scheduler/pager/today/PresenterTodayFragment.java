package ru.eva.oriokslive.fragmens.scheduler.pager.today;

import ru.eva.oriokslive.helpers.ConvertHelper;

class PresenterTodayFragment implements ContractTodayFragment.Presenter{
    private ContractTodayFragment.View mView;
    private ContractTodayFragment.Repository mRepository;


    PresenterTodayFragment(ContractTodayFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryTodayFragment();
    }

    @Override
    public void getSchedule() {
        int currentDay = ConvertHelper.getInstance().calculateCurrentDay();
        int currentDayOfWeek = ConvertHelper.getInstance().getDayOfWeek();
        mView.setAdapter(mRepository.getSchedule(currentDay, currentDayOfWeek));
    }


}
