package ru.eva.oriokslive.fragmens.scheduler.pager.seconddenominator;

public class PresenterSecondDenominationFragment implements ContractSecondDenominationFragment.Presenter{
    private ContractSecondDenominationFragment.View mView;
    private ContractSecondDenominationFragment.Repository mRepository;


    PresenterSecondDenominationFragment(ContractSecondDenominationFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositorySecondDenominationFragment();
    }

    @Override
    public void getSchedule() {
        mView.setAdapter(mRepository.getSchedule());
    }
}
