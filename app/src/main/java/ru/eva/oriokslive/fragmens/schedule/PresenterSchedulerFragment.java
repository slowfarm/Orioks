package ru.eva.oriokslive.fragmens.schedule;

import java.util.List;

class PresenterSchedulerFragment implements ContractSchedulerFragment.Presenter {
    private ContractSchedulerFragment.View mView;
    private ContractSchedulerFragment.Repository mRepository;


    PresenterSchedulerFragment(ContractSchedulerFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositorySchedulerFragment();
    }

    @Override
    public void getRecyclerView() {
        List<String> groupList = mRepository.getLocalGroups();
        mView.setRecyclerView(groupList);
    }

    @Override
    public void addLocalGroup(String group) {
        mRepository.addLocalGroup(group);
        mView.notifyDataSetChanged(mRepository.getLocalGroups());
    }

    @Override
    public void removeGroup(String group) {
        mRepository.removeGroup(group);
    }
}
