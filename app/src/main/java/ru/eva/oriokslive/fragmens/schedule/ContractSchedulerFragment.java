package ru.eva.oriokslive.fragmens.schedule;

import java.util.List;

class ContractSchedulerFragment {
    interface View {

        void setRecyclerView(List<String> groupList);

        void notifyDataSetChanged(List<String> groupList);
    }

    interface Presenter {

        void getRecyclerView();

        void addLocalGroup(String group);

        void removeGroup(String group);
    }

    interface Repository {
        List<String> getLocalGroups();

        void addLocalGroup(String group);

        void removeGroup(String group);
    }
}
