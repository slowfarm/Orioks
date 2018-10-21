package ru.eva.oriokslive.fragmens.Student;

import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Student;

class PresenterStudentFragment implements ContractStudentFragment.Presenter, OnTokenRecieved {
    private ContractStudentFragment.View mView;
    private ContractStudentFragment.Repository mRepository;


    PresenterStudentFragment(ContractStudentFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryStudentFragment();
    }

    @Override
    public void getStudent() {
        Student student = mRepository.getStudent();
        if(student != null) {
            mView.setStudent(student);
        }
    }

    @Override
    public void onButtonWasClicked() {
        mRepository.deleteToken(this);
    }

    @Override
    public void onResponse(AccessToken accessToken) {
        finishApp();
    }

    @Override
    public void onFailure(Throwable t) {
        finishApp();
    }

    private void finishApp(){
        mRepository.clearAllTables();
        mView.finishActivity();
    }
}
