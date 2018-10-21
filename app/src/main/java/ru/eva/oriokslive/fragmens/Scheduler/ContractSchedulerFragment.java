package ru.eva.oriokslive.fragmens.Scheduler;

import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnViewPagerChangeListener;
import ru.eva.oriokslive.models.schedule.Schedulers;

class ContractSchedulerFragment {
    interface View {

        void setPagerAdapter(int currentDay);

        void showToast(String text);

        void onPageChange(int i);
    }

    interface Presenter {

        void getCurrentDay();

        void getSchedule();

        void onPageChange(int i,  OnViewPagerChangeListener onViewPagerChangeListener);
    }

    interface Repository {

        void getSchedule(OnSchedulersReceived onSchedulersReceived);

        void setSchedule(Schedulers schedulers);

        Schedulers getLocalSchedule();
    }
}
