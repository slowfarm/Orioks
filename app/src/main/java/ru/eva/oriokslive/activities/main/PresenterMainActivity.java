package ru.eva.oriokslive.activities.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.eva.oriokslive.models.orioks.Student;

class PresenterMainActivity implements ContractMainActivity.Presenter {

    private ContractMainActivity.View mView;
    private ContractMainActivity.Repository mRepository;

    PresenterMainActivity(ContractMainActivity.View mView) {
        this.mView = mView;
        this.mRepository = new RepositoryMainActivity();
    }

    @Override
    public void setCurrentWeek() {
        int currentWeek = getCurrentWeek();
        mView.showCurrentWeek(String.valueOf(currentWeek), getProgress(), getCurrentValue(currentWeek));
    }

    @Override
    public void getStudent() {
        Student student = mRepository.getStudent();
        if(student != null) {
            mView.setStudent(student.getFullName(), student.getGroup());
        }
    }

    private float getProgress() {
        return (float)getCurrentWeek()/18*100;
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
        int week = currentWeek - startWeek + 1;
        if(week > 18) return 18;
        return week;
    }

    private String getCurrentValue(int week) {
        String value;
        switch (week % 4) {
            case 0:
                value = "2 Знаменатель";
                break;
            case 1:
                value = "1 Числитель";
                break;
            case 2:
                value = "1 Знаменатель";
                break;
            case 3:
                value = "2 Числитель";
                break;
            default:
                value = "1 Числитель";
                break;
        }
        return  value;
    }
}