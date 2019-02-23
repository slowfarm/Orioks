package ru.eva.oriokslive.fragmens.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.models.schedule.Schedulers;

class PresenterSchedulerFragment implements ContractSchedulerFragment.Presenter, OnSchedulersReceived {
    private ContractSchedulerFragment.View mView;
    private ContractSchedulerFragment.Repository mRepository;


    PresenterSchedulerFragment(ContractSchedulerFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositorySchedulerFragment();
    }

    @Override
    public void setPagerAdapter() {
        if(mRepository.getLocalSchedule() == null) {
            mRepository.getSchedule(this);
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
