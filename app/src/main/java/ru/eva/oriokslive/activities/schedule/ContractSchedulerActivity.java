package ru.eva.oriokslive.activities.schedule;

import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.models.miet.schedule.Schedulers;

class ContractSchedulerActivity {
    interface View {

        void setPagerAdapter();

        void showToast(String text);

        void setViewPagerToPosition(int position);
    }

    interface Presenter {

        void setPagerAdapter(String group);

        void setViewPagerToCurrentWeek();
    }

    interface Repository {

        void getSchedule(OnSchedulersReceived onSchedulersReceived, String group);

        void setSchedule(Schedulers schedulers);

        Schedulers getLocalSchedule(String group);
    }
}
