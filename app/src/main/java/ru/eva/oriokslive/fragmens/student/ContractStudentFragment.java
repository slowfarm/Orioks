package ru.eva.oriokslive.fragmens.student;

import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.Student;

class ContractStudentFragment {
    interface View {

        void setStudent(Student student);

        void finishActivity();
    }

    interface Presenter {

        void getStudent();

        void onButtonWasClicked();
    }

    interface Repository {

        Student getStudent();

        void clearAllTables();

        void deleteToken(OnTokenRecieved onTokenRecieved);
    }
}
