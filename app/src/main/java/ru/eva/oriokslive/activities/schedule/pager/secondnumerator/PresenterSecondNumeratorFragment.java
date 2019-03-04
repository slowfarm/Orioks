package ru.eva.oriokslive.activities.schedule.pager.secondnumerator;

import java.util.List;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.models.miet.schedule.Data;

public class PresenterSecondNumeratorFragment implements ContractSecondNumeratorFragment.Presenter{
    private ContractSecondNumeratorFragment.View mView;
    private ContractSecondNumeratorFragment.Repository mRepository;


    PresenterSecondNumeratorFragment(ContractSecondNumeratorFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositorySecondNumeratorFragment();
    }

    @Override
    public void getSchedule(String group) {
        List<Data> dataList = ConvertHelper.getInstance().scheduleWeek(mRepository.getSchedule(group));
        mView.setAdapter(dataList);
    }
}
