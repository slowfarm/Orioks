package ru.eva.oriokslive.activities.main;

import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.schedule.Schedulers;

class ContractMainActivity {
    interface View {
        void showCurrentWeek(String currentWeek, float progress, String value);

        void setStudent(String name, String group);

        void showToast(String text);

        void setToolbarTitle(String title);
    }

    interface Presenter {
        void setCurrentWeek();

        void getStudent();

        void setStudent();

        void getToolbarTitle(int position);

        void getSchedule();
    }

    interface Repository {

        Student getStudent();

        void updateStudent(OnStudentRecieved onStudentRecieved);

        void setStudent(Student student);

        void getSchedule(String group, OnSchedulersReceived onSchedulersReceived);

        void setSchedule(Schedulers schedulers);
    }
}
