package ru.eva.oriokslive.activities.schedule.pager.firstnumerator;

import java.util.List;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.models.miet.schedule.Data;

public class PresenterFirstNumeratorFragment implements ContractFirstNumeratorFragment.Presenter{
    private ContractFirstNumeratorFragment.View mView;
    private ContractFirstNumeratorFragment.Repository mRepository;


    PresenterFirstNumeratorFragment(ContractFirstNumeratorFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryFirstNumeratorFragment();
    }

    @Override
    public void getSchedule(String group) {
        List<Data> dataList = ConvertHelper.getInstance().scheduleWeek(mRepository.getSchedule(group));
        mView.setAdapter(dataList);
    }
}
