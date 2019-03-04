package ru.eva.oriokslive.interfaces;

public interface OnGroupsReceived {
    void onResponse(String[] group);

    void onFailure(Throwable t);
}
