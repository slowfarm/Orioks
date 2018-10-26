package ru.eva.oriokslive.fragmens.scheduler.pager.firstdenomination;

import java.util.List;

import ru.eva.oriokslive.models.schedule.Data;

class ContractFirstDenominationFragment {

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
