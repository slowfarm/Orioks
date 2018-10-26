package ru.eva.oriokslive.fragmens.scheduler.pager.secondnumerator;

public class PresenterSecondNumeratorFragment implements ContractSecondNumeratorFragment.Presenter{
    private ContractSecondNumeratorFragment.View mView;
    private ContractSecondNumeratorFragment.Repository mRepository;


    PresenterSecondNumeratorFragment(ContractSecondNumeratorFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositorySecondNumeratorFragment();
    }

    @Override
    public void getSchedule() {
        mView.setAdapter(mRepository.getSchedule());
    }
}
