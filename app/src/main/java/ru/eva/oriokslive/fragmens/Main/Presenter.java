package ru.eva.oriokslive.fragmens.Main;

import java.util.List;

import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.models.orioks.Disciplines;

class Presenter implements Contract.Presenter, OnDisciplinesRecieved {
    private Contract.View mView;
    private Contract.Repository mRepository;

    public Presenter(Contract.View mView) {
        this.mView = mView;
        mRepository = new Repository();
    }

    @Override
    public void getDisciplineList() {
        mView.setRecyclerView(mRepository.getDisciplineList());
    }

    @Override
    public void setDisciplineList() {
        mRepository.setDisciplineList(this);

    }

    @Override
    public void onResponse(List<Disciplines> disciplinesList) {
        if(disciplinesList != null) {
            mRepository.setDisciplineList(disciplinesList);
            mView.addRecyclerVIewItems(disciplinesList);
        } else {
            mView.showToast("Ошибка обновления");
        }
        mView.unsetRefreshing();
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast(t.getMessage());
        mView.unsetRefreshing();
    }
}
