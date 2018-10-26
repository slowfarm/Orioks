package ru.eva.oriokslive.fragmens.scheduler.pager.secondnumerator;

import java.util.List;

import ru.eva.oriokslive.models.schedule.Data;

class ContractSecondNumeratorFragment {

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
