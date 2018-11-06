package ru.eva.oriokslive.activities.splash;

import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.schedule.Schedulers;

class ContractSplashActivity {
    interface View {
        void startMainActivity();

        void startRegistrationActivity();

        void showToast(String error);
    }

    interface Presenter {
        void checkAccessToken();
    }

    interface Repository {
        String getAccessToken();

        void getStudent(OnStudentRecieved onStudentRecieved);

        void getSchedule(String group, OnSchedulersReceived onSchedulersReceived);

        void setSchedule(Schedulers schedulers);

        void setStudent(Student student);

        void deleteToken(OnTokenRecieved onTokenRecieved);

        void clearAllTables();
    }
}
