package ru.eva.oriokslive.fragmens.scheduler.pager.firstnumerator;

public class PresenterFirstNumeratorFragment implements ContractFirstNumeratorFragment.Presenter{
    private ContractFirstNumeratorFragment.View mView;
    private ContractFirstNumeratorFragment.Repository mRepository;


    PresenterFirstNumeratorFragment(ContractFirstNumeratorFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryFirstNumeratorFragment();
    }

    @Override
    public void getSchedule() {
        mView.setAdapter(mRepository.getSchedule());
    }
}
