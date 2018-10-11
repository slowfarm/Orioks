package ru.eva.oriokslive.interfaces;

import java.util.List;

import ru.eva.oriokslive.models.orioks.Disciplines;

public interface OnDisciplinesRecieved {
    void onResponse(List<Disciplines> disciplinesList);

    void onFailure(Throwable t);
}
