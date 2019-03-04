package ru.eva.oriokslive.fragmens.schedule;

import java.util.List;

import ru.eva.oriokslive.domain.LocalStorage;

class RepositorySchedulerFragment implements ContractSchedulerFragment.Repository {

    @Override
    public List<String> getLocalGroups() {
        return LocalStorage.getInstance().getLocalGroups();
    }

    @Override
    public void addLocalGroup(String group) {
        LocalStorage.getInstance().addLocalGroup(group);
    }

    @Override
    public void removeGroup(String group) {
        LocalStorage.getInstance().removeGroup(group);
    }
}
