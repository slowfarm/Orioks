package ru.eva.oriokslive.activities.registration;

import java.util.List;

import ru.eva.oriokslive.interfaces.OnAllAccessTokensReceived;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Security;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.miet.schedule.Schedulers;

class ContractRegistrationActivity {
    interface View {
        void startActivity();

        void showToast(String text);

        void setButtonEnabled();
    }

    interface Presenter {

        void onButtonWasClicked(String login, String password);
    }

    interface Repository {
        void getAccessToken(String encodedString, OnTokenRecieved onTokenRecieved);

        void setAccessToken(AccessToken token);

        void getStudent(OnStudentRecieved onStudentRecieved);

        void setSchedule(Schedulers schedulers);

        void setStudent(Student student);

        void getSchedule(String group, OnSchedulersReceived onSchedulersReceived);

        void getAllActiveTokens(OnAllAccessTokensReceived onAllAccessTokensReceived);

        void setAllActiveTokens(List<Security> tokens);
    }
}
