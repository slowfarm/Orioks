package ru.eva.oriokslive.activities.group;

import java.util.List;

import ru.eva.oriokslive.interfaces.OnGroupsReceived;

class ContractGroupActivity {
    interface View {
        void setRecyclerView(List<String> groupList);

        void showToast(String text);
    }

    interface Presenter {
        void getRecyclerView();
    }

    interface Repository {
        void getGroups(OnGroupsReceived onGroupsReceived);
    }
}
