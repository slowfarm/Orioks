package ru.eva.oriokslive.activities.schedule.pager.firstnumerator;

import java.util.List;

import ru.eva.oriokslive.models.miet.schedule.Data;

class ContractFirstNumeratorFragment {

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
