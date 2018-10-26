package ru.eva.oriokslive.fragmens.scheduler.pager.seconddenominator;

import java.util.List;

import ru.eva.oriokslive.models.schedule.Data;

class ContractSecondDenominationFragment {

    interface View {

        void setAdapter(List<Data> schedule);
    }

    interface Presenter {

        void getSchedule();
    }

    interface Repository {

        List<Data> getSchedule();
    }
}
