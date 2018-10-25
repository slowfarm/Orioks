package ru.eva.oriokslive.fragmens.Scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnViewPagerChangeListener;
import ru.eva.oriokslive.models.schedule.Schedulers;

class PresenterSchedulerFragment implements ContractSchedulerFragment.Presenter, OnSchedulersReceived {
    private ContractSchedulerFragment.View mView;
    private ContractSchedulerFragment.Repository mRepository;


    PresenterSchedulerFragment(ContractSchedulerFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositorySchedulerFragment();
    }

    @Override
    public void getCurrentDay() {
        mView.setPagerAdapter(calculateCurrentDay());
    }

    @Override
    public void getSchedule() {
        if(mRepository.getLocalSchedule() == null) {
            mRepository.getSchedule(this);
        } else {
            mView.setPagerAdapter(calculateCurrentDay());
        }
    }

    @Override
    public void onPageChange(int i, OnViewPagerChangeListener onViewPagerChangeListener) {
        if(onViewPagerChangeListener != null)
            mView.onPageChange(i);
    }

    @Override
    public void setViewPagerToPosition() {
        int position = (getCurrentWeek()-1)%4 + 2;
        mView.setViewPagerToPosition(position);
    }

    @Override
    public void refreshSchedule() {
        mRepository.getSchedule(this);
    }

    @Override
    public void onResponse(Schedulers schedulers) {
        if(schedulers != null) {
            mRepository.setSchedule(schedulers);
            mView.setPagerAdapter(calculateCurrentDay());
            mView.onPageChange(0);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
    }

    private int getCurrentWeek() {
        String format = "yyyyMMdd";

        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        String input = cal.get(Calendar.YEAR)+"0901";
        Date date = null;
        try {
            date = df.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        int startWeek = cal.get(Calendar.WEEK_OF_YEAR);
        cal.setTime(new Date());
        int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
        return currentWeek - startWeek + 1;
    }

    private int calculateCurrentDay() {
        int currentWeek = getCurrentWeek();
        return (currentWeek-1)%4;
    }
}
