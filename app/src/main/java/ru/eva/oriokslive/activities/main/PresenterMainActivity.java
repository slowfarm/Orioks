package ru.eva.oriokslive.activities.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.eva.oriokslive.helpers.ConvertHelper;
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
        int currentWeek = ConvertHelper.getInstance().getCurrentWeek();
        String currentValue = ConvertHelper.getInstance().getCurrentValue(currentWeek);
        mView.showCurrentWeek(String.valueOf(currentWeek), getProgress(), currentValue);
    }

    @Override
    public void getStudent() {
        Student student = mRepository.getStudent();
        if(student != null) {
            mView.setStudent(student.getFullName(), student.getGroup());
        }
    }

    private float getProgress() {
        return (float)ConvertHelper.getInstance().getCurrentWeek()/18*100;
    }


}