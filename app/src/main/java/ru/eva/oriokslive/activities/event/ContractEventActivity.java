package ru.eva.oriokslive.activities.event;

import java.util.List;

import ru.eva.oriokslive.helpers.DialogHelper;
import ru.eva.oriokslive.interfaces.OnEventsRecieved;
import ru.eva.oriokslive.models.orioks.Disciplines;
import ru.eva.oriokslive.models.orioks.Events;

class ContractEventActivity {
    interface View {
        void setRecyclerView(List<Events> data);

        void showDialog(Disciplines discipline, DialogHelper dialog);

        void setToolbarTitle(String title);

        void addRecyclerViewItems(List<Events> data);

        void showToast(String text);
    }

    interface Presenter {

        void getRecyclerView();

        void optionsItemSelected();

        void getToolbarTitle();

        void getEvents();
    }

    interface Repository {
        List<Events> getEventList(int id);

        Disciplines getDiscipline(int id);

        void getEventList(int id, OnEventsRecieved onEventsRecieved);

        void setEventList(List<Events> eventsList);
    }
}
