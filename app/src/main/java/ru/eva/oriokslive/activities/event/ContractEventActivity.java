package ru.eva.oriokslive.activities.event;

import java.util.List;

import ru.eva.oriokslive.helpers.DialogHelper;
import ru.eva.oriokslive.interfaces.OnEventsRecieved;
import ru.eva.oriokslive.models.orioks.Discipline;
import ru.eva.oriokslive.models.orioks.Event;

class ContractEventActivity {
    interface View {
        void setRecyclerView(List<Event> data);

        void showDialog(Discipline discipline, DialogHelper dialog);

        void setToolbarTitle(String title);

        void addRecyclerViewItems(List<Event> data);

        void showToast(String text);
    }

    interface Presenter {

        void getRecyclerView();

        void optionsItemSelected();

        void getToolbarTitle();

        void getEvents();
    }

    interface Repository {
        List<Event> getEventList(int id);

        Discipline getDiscipline(int id);

        void getEventList(int id, OnEventsRecieved onEventsRecieved);

        void setEventList(List<Event> eventList);
    }
}
