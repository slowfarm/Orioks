package ru.eva.oriokslive.fragmens.Student;

import ru.eva.oriokslive.models.orioks.Student;

class PresenterStudentFragment implements ContractStudentFragment.Presenter {
    private ContractStudentFragment.View mView;
    private ContractStudentFragment.Repository mRepository;


    public PresenterStudentFragment(ContractStudentFragment.View mView) {
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
        mRepository.clearAllTables();
        mView.finishActivity();
    }
}
