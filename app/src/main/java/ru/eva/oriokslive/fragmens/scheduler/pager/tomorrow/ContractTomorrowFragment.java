package ru.eva.oriokslive.fragmens.scheduler.pager.tomorrow;

import java.util.List;

import ru.eva.oriokslive.models.schedule.Data;

class ContractTomorrowFragment {

    interface View {

        void setAdapter(List<Data> schedule);
    }

    interface Presenter {

        void getSchedule();
    }

    interface Repository {

        List<Data> getSchedule(int week, int day);
    }
}
