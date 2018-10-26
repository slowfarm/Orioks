package ru.eva.oriokslive.activities.event;

import java.util.List;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnEventsRecieved;
import ru.eva.oriokslive.models.orioks.Disciplines;
import ru.eva.oriokslive.models.orioks.Events;

class RepositoryEventActivity implements ContractEventActivity.Repository {
    @Override
    public List<Events> getEventList(int id) {
        return StorageHelper.getInstance().getEventsList(id);
    }

    @Override
    public Disciplines getDiscipline(int id) {
        return StorageHelper.getInstance().getDiscipline(id);
    }

    @Override
    public void getEventList(int id, OnEventsRecieved onEventsRecieved) {
        RetrofitHelper.getInstance().setOnEventsReceived(onEventsRecieved);
        RetrofitHelper.getInstance().getEvents(StorageHelper.getInstance().getAccessToken(), id);
    }

    @Override
    public void setEventList(List<Events> eventsList) {
        StorageHelper.getInstance().setEvents(eventsList);
    }
}
