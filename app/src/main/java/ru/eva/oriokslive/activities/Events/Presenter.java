package ru.eva.oriokslive.activities.Events;

import android.content.Context;

import java.util.List;

import ru.eva.oriokslive.helpers.DialogHelper;
import ru.eva.oriokslive.interfaces.OnEventsRecieved;
import ru.eva.oriokslive.models.orioks.Events;

class Presenter implements Contract.Presenter, OnEventsRecieved {
    private Contract.View mView;
    private Contract.Repository mRepository;
    private int id;

    Presenter(Contract.View mView, int id) {
        this.mView = mView;
        this.mRepository = new Repository();
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
    public void getEvents(Context context) {
        mRepository.getEventList(context, id, this);
    }

    @Override
    public void onResponse(List<Events> eventsList) {
        if(isListsNotEqual(eventsList)) {
            mView.addRecyclerViewItems(eventsList);
            for (Events event : eventsList)
                event.setId(id);
            mRepository.setEventList(eventsList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mView.showToast(t.getMessage());
    }

    private boolean isListsNotEqual(List<Events> retrofitList) {
        List<Events> repositoryList = mRepository.getEventList(id);
        if(repositoryList.size() != retrofitList.size()) {
            return true;
        } else {
            for(int i=0; i < repositoryList.size(); i++) {
                if(!repositoryList.get(i).getCurrentGrade().equals(retrofitList.get(i).getCurrentGrade()))
                    return true;
            }
            return false;
        }
    }
}
