package ru.eva.oriokslive.fragmens.Student;

import ru.eva.oriokslive.models.orioks.Student;

class Presenter implements Contract.Presenter {
    private Contract.View mView;
    private Contract.Repository mRepository;


    public Presenter(Contract.View mView) {
        this.mView = mView;
        mRepository = new Repository();
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
