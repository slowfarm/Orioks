package ru.eva.oriokslive.activities.schedule.pager.tomorrow;

import java.util.List;

import ru.eva.oriokslive.models.miet.schedule.Data;

class ContractTomorrowFragment {

    interface View {

        void setAdapter(List<Data> schedule);
    }

    interface Presenter {

        void getSchedule(String group);
    }

    interface Repository {

        List<Data> getSchedule(int week, int day, String group);
    }
}
