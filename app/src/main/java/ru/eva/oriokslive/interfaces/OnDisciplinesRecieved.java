package ru.eva.oriokslive.interfaces;

import java.util.List;

import ru.eva.oriokslive.models.orioks.Discipline;

public interface OnDisciplinesRecieved {
    void onResponse(List<Discipline> disciplineList);

    void onFailure(Throwable t);
}
