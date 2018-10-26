package ru.eva.oriokslive.fragmens.scheduler;

import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.models.schedule.Schedulers;

class ContractSchedulerFragment {
    interface View {

        void setPagerAdapter();

        void showToast(String text);

        void setViewPagerToPosition(int position);
    }

    interface Presenter {

        void setPagerAdapter();

        void setViewPagerToCurrentWeek();

        void refreshSchedule();
    }

    interface Repository {

        void getSchedule(OnSchedulersReceived onSchedulersReceived);

        void setSchedule(Schedulers schedulers);

        Schedulers getLocalSchedule();
    }
}
