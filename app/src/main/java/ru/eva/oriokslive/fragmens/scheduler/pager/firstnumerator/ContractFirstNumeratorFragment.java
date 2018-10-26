package ru.eva.oriokslive.fragmens.scheduler.pager.firstnumerator;

import java.util.List;

import ru.eva.oriokslive.models.schedule.Data;

class ContractFirstNumeratorFragment {

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
