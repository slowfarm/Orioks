package ru.eva.oriokslive.fragmens.scheduler.pager.tomorrow;

import ru.eva.oriokslive.helpers.ConvertHelper;

class PresenterTomorrowFragment implements ContractTomorrowFragment.Presenter{
    private ContractTomorrowFragment.View mView;
    private ContractTomorrowFragment.Repository mRepository;


    PresenterTomorrowFragment(ContractTomorrowFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryTomorrowFragment();
    }

    @Override
    public void getSchedule() {
        int currentDay = ConvertHelper.getInstance().calculateCurrentDay();
        int nextDayOfWeek = ConvertHelper.getInstance().getNextDayOfWeek();
        mView.setAdapter(mRepository.getSchedule(currentDay, nextDayOfWeek));
    }
}
