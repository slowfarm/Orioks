package ru.eva.oriokslive.fragmens.Scheduler;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.models.schedule.Schedulers;

class Presenter implements Contract.Presenter, OnSchedulersReceived {
    private Contract.View mView;
    private Contract.Repository mRepository;


    public Presenter(Contract.View mView) {
        this.mView = mView;
        mRepository = new Repository();
    }

    @Override
    public void getCurrentDay(Context context) {
        mView.setPagerAdapter(calculateCurrentDay());
    }

    @Override
    public void checkSchedulerIsEmpty(Context context) {
        if(mRepository.getSchedulersDataCurrentWeek(getCurrentWeek()).size() == 0) {
           mRepository.getSchedule(this);
        }
    }
    @Override
    public void onResponse(Schedulers schedulers) {
        mRepository.setSchedule(schedulers);
        mView.setPagerAdapter(calculateCurrentDay());
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast(t.getMessage());
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
