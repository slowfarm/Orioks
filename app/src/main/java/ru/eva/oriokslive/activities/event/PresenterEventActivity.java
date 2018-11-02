package ru.eva.oriokslive.activities.event;

import java.util.List;

import ru.eva.oriokslive.helpers.DialogHelper;
import ru.eva.oriokslive.interfaces.OnEventsRecieved;
import ru.eva.oriokslive.models.orioks.Events;

class PresenterEventActivity implements ContractEventActivity.Presenter, OnEventsRecieved {
    private ContractEventActivity.View mView;
    private ContractEventActivity.Repository mRepository;
    private int id;

    PresenterEventActivity(ContractEventActivity.View mView, int id) {
        this.mView = mView;
        this.mRepository = new RepositoryEventActivity();
        this.id = id;
    }

    @Override
    public void getRecyclerView() {
        mView.setRecyclerView(mRepository.getEventList(id));
    }

    @Override
    public void optionsItemSelected() {
        mView.showDialog(mRepository.getDiscipline(id), DialogHelper.getInstance());
    }

    @Override
    public void getToolbarTitle() {
        mView.setToolbarTitle(mRepository.getDiscipline(id).getName());
    }

    @Override
    public void getEvents() {
        mRepository.getEventList(id, this);
    }

    @Override
    public void onResponse(List<Events> eventsList) {
        if(eventsList != null) {
            mView.addRecyclerViewItems(eventsList);
            for (Events event : eventsList)
                event.setId(id);
            mRepository.setEventList(eventsList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast("Нет соединения с интернетом");
    }
}
