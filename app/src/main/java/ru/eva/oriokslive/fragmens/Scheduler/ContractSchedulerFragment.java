package ru.eva.oriokslive.fragmens.Scheduler;

import java.util.List;

import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.models.schedule.Data;
import ru.eva.oriokslive.models.schedule.Schedulers;

class ContractSchedulerFragment {
    interface View {

        void setPagerAdapter(int currentDay);

        void showToast(String text);
    }

    interface Presenter {

        void getCurrentDay();

        void getSchedule();
    }

    interface Repository {

        void getSchedule(OnSchedulersReceived onSchedulersReceived);

        void setSchedule(Schedulers schedulers);

        Schedulers getLocalSchedule();
    }
}
