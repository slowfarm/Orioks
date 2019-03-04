package ru.eva.oriokslive.activities.group;

import ru.eva.oriokslive.domain.NetworkStorage;
import ru.eva.oriokslive.interfaces.OnGroupsReceived;

class RepositoryGroupActivity implements ContractGroupActivity.Repository {

    @Override
    public void getGroups(OnGroupsReceived onGroupsReceived) {
        NetworkStorage.getInstance().setOnGroupsReceived(onGroupsReceived);
        NetworkStorage.getInstance().getGroups();
    }
}
