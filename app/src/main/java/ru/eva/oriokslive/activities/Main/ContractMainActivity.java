package ru.eva.oriokslive.activities.Main;

import android.content.Context;

import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.models.orioks.Student;

class ContractMainActivity {
    interface View {
        void showCurrentWeek(String currentWeek, float progress, String value);

        void setViewPagerToPosition(int position);

        void setStudent(String name, String group);

        void showToast(String text);

        void setToolbarTitle(String title);
    }

    interface Presenter {
        void setCurrentWeek();

        void setViewPagerToPosition();

        void getStudent();

        void setStudent();

        void getToolbarTitle(int position);
    }

    interface Repository {

        Student getStudent();

        void updateStudent(OnStudentRecieved onStudentRecieved);

        void setStudent(Student student);
    }
}