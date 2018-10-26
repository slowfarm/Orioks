package ru.eva.oriokslive.fragmens.scheduler.pager.firstdenomination;

public class PresenterFirstDenominationFragment implements ContractFirstDenominationFragment.Presenter{
    private ContractFirstDenominationFragment.View mView;
    private ContractFirstDenominationFragment.Repository mRepository;


    PresenterFirstDenominationFragment(ContractFirstDenominationFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryFirstDenominationFragment();
    }

    @Override
    public void getSchedule() {
        mView.setAdapter(mRepository.getSchedule());
    }
}
