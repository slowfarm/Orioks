package ru.eva.oriokslive.activities.main;

import ru.eva.oriokslive.models.orioks.Student;

class ContractMainActivity {
    interface View {
        void showCurrentWeek(String currentWeek, float progress, String value);

        void setStudent(String name, String group);

        void showToast(String text);
    }

    interface Presenter {
        void setCurrentWeek();

        void getStudent();
    }

    interface Repository {

        Student getStudent();
    }
}
