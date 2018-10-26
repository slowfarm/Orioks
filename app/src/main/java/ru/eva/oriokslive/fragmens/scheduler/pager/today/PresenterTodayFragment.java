package ru.eva.oriokslive.fragmens.scheduler.pager.today;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

class PresenterTodayFragment implements ContractTodayFragment.Presenter{
    private ContractTodayFragment.View mView;
    private ContractTodayFragment.Repository mRepository;


    PresenterTodayFragment(ContractTodayFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryTodayFragment();
    }

    @Override
    public void getSchedule() {
        mView.setAdapter(mRepository.getSchedule(calculateCurrentDay(), getDayOfWeek()));
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

    private int getDayOfWeek() {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }
}
