package ru.eva.oriokslive.activities.news;

public class PresenterNewsActivity implements ContractNewsActivity.Presenter{
    private ContractNewsActivity.View mView;
    private ContractNewsActivity.Repository mRepository;

    PresenterNewsActivity(ContractNewsActivity.View mView) {
        this.mView = mView;
        mRepository = new RepositoryNewsActivity();
    }
}
