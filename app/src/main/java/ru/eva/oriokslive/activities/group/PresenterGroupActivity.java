package ru.eva.oriokslive.activities.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.eva.oriokslive.interfaces.OnGroupsReceived;

class PresenterGroupActivity implements ContractGroupActivity.Presenter, OnGroupsReceived {
    private ContractGroupActivity.View mView;
    private ContractGroupActivity.Repository mRepository;

    PresenterGroupActivity(ContractGroupActivity.View mView) {
        this.mView = mView;
        this.mRepository = new RepositoryGroupActivity();
    }

    @Override
    public void getRecyclerView() {
        mRepository.getGroups(this);
    }

    @Override
    public void onResponse(String[] groups) {
        List<String> groupList = new ArrayList<>();
        Collections.addAll(groupList, groups);
        mView.setRecyclerView(groupList);
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
    }
}
