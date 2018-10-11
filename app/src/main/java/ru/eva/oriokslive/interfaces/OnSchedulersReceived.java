package ru.eva.oriokslive.interfaces;

import ru.eva.oriokslive.models.schedule.Schedulers;

public interface OnSchedulersReceived {

    void onResponse(Schedulers schedulers);
    void onFailure(Throwable t);
}
