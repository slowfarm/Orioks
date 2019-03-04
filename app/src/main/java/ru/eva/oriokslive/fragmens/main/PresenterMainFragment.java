package ru.eva.oriokslive.fragmens.main;

import java.util.List;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Discipline;

class PresenterMainFragment implements ContractMainFragment.Presenter, OnDisciplinesRecieved, OnTokenRecieved {
    private ContractMainFragment.View mView;
    private ContractMainFragment.Repository mRepository;

    PresenterMainFragment(ContractMainFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryMainFragment();
    }

    @Override
    public void getDisciplineList() {
        mView.setRecyclerView(ConvertHelper.getInstance().disciplines(mRepository.getDisciplineList()));
    }

    @Override
    public void setDisciplineList() {
        mRepository.setDisciplineList(this);
    }

    @Override
    public void onResponse(List<Discipline> disciplineList) {
        if(disciplineList != null) {
            mRepository.setDisciplineList(disciplineList);
            mView.addRecyclerVIewItems(ConvertHelper.getInstance().disciplines(disciplineList));
        } else {
            mView.showToast("Токен аннулирован");
            mRepository.deleteToken(this);
        }
        mView.unsetRefreshing();
    }

    @Override
    public void onResponse(AccessToken accessToken) {
        finishApp();
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
        mView.unsetRefreshing();
    }

    private void finishApp(){
        mRepository.clearAllTables();
        mView.finishActivity();
    }
}
