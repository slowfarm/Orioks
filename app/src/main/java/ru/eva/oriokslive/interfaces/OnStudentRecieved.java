package ru.eva.oriokslive.interfaces;

import ru.eva.oriokslive.models.orioks.Student;

public interface OnStudentRecieved {
    void onResponse(Student student);

    void onFailure(Throwable t);
}
