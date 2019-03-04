package ru.eva.oriokslive.activities.schedule.pager.firstdenomination;

import java.util.List;

import ru.eva.oriokslive.models.miet.schedule.Data;

class ContractFirstDenominationFragment {

    interface View {

        void setAdapter(List<Data> schedule);
    }

    interface Presenter {

        void getSchedule(String group);
    }

    interface Repository {

        List<Data> getSchedule(String group);
    }
}
