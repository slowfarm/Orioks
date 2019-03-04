package ru.eva.oriokslive.interfaces;

import java.util.List;

import ru.eva.oriokslive.models.orioks.Event;

public interface OnEventsRecieved {
    void onResponse(List<Event> eventList);

    void onFailure(Throwable t);
}
