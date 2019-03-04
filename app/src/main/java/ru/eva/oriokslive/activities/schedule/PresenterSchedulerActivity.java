package ru.eva.oriokslive.activities.schedule;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.models.miet.schedule.Schedulers;

class PresenterSchedulerActivity implements ContractSchedulerActivity.Presenter, OnSchedulersReceived {
    private ContractSchedulerActivity.View mView;
    private ContractSchedulerActivity.Repository mRepository;


    PresenterSchedulerActivity(ContractSchedulerActivity.View mView) {
        this.mView = mView;
        mRepository = new RepositorySchedulerActivity();
    }

    @Override
    public void setPagerAdapter(String group) {
        if(mRepository.getLocalSchedule(group) == null) {
            mRepository.getSchedule(this, group);
        } else {
            mView.setPagerAdapter();
        }
    }

    @Override
    public void setViewPagerToCurrentWeek() {
        int currentWeek = ConvertHelper.getInstance().getCurrentWeek();
        int position = (currentWeek-1)%4 + 2;
        mView.setViewPagerToPosition(position);
    }

    @Override
    public void onResponse(Schedulers schedulers) {
        if(schedulers != null) {
            mRepository.setSchedule(schedulers);
            mView.setPagerAdapter();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
    }

}
