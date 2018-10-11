package ru.eva.oriokslive.interfaces;

import java.util.List;

import ru.eva.oriokslive.models.orioks.Events;

public interface OnEventsRecieved {
    void onResponse(List<Events> eventsList);

    void onFailure(Throwable t);
}
