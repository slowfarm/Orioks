package ru.eva.oriokslive.activities.schedule.pager.seconddenominator;

import java.util.List;

import ru.eva.oriokslive.models.miet.schedule.Data;

class ContractSecondDenominationFragment {

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
