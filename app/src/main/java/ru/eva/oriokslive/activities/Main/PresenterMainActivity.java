package ru.eva.oriokslive.activities.Main;

import android.view.Menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.models.orioks.Student;

class PresenterMainActivity implements ContractMainActivity.Presenter, OnStudentRecieved {

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
    public void setViewPagerToPosition() {
        mView.setViewPagerToPosition((getCurrentWeek()-1)%4);
    }

    @Override
    public void getStudent() {
        Student student = mRepository.getStudent();
        if(student != null) {
            mView.setStudent(student.getFullName(), student.getGroup());
        }
    }

    @Override
    public void setStudent() {
        mRepository.updateStudent(this);

    }

    @Override
    public void getToolbarTitle(int position) {
        mView.setToolbarTitle(getToolbarTitleByPosition(position));
    }

    @Override
    public void onResume(Menu menu) {
        if(menu != null)
            mView.hideMenu();
    }

    @Override
    public void onResponse(Student student) {
        if(student != null) {
            if(student.getError() != null) {
                mView.showToast(student.getError());
            }
            else {
                mView.setStudent(student.getFullName(), student.getGroup());
                mRepository.setStudent(student);
            }
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast(t.getMessage());
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
        return currentWeek - startWeek + 1;
    }

    private String getToolbarTitleByPosition(int position) {
        switch (position) {
            case 0:
                return "Сегодня";
            case 1:
                return "Завтра";
            case 2:
                return "1 Числитель";
            case 3:
                return "1 Знаменатель";
            case 4:
                return "2 Числитель";
            case 5:
                return "2 Знаменатель";
                default:
                    return "";
        }
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