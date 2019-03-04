package ru.eva.oriokslive.activities.event;

import java.util.List;

import ru.eva.oriokslive.domain.NetworkStorage;
import ru.eva.oriokslive.domain.LocalStorage;
import ru.eva.oriokslive.interfaces.OnEventsRecieved;
import ru.eva.oriokslive.models.orioks.Discipline;
import ru.eva.oriokslive.models.orioks.Event;

class RepositoryEventActivity implements ContractEventActivity.Repository {
    @Override
    public List<Event> getEventList(int id) {
        return LocalStorage.getInstance().getEventsList(id);
    }

    @Override
    public Discipline getDiscipline(int id) {
        return LocalStorage.getInstance().getDiscipline(id);
    }

    @Override
    public void getEventList(int id, OnEventsRecieved onEventsRecieved) {
        NetworkStorage.getInstance().setOnEventsReceived(onEventsRecieved);
        NetworkStorage.getInstance().getEvents(LocalStorage.getInstance().getAccessToken(), id);
    }

    @Override
    public void setEventList(List<Event> eventList) {
        LocalStorage.getInstance().setEvents(eventList);
    }
}
