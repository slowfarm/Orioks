package ru.eva.oriokslive.activities.schedule.pager.seconddenominator;

import java.util.List;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.models.miet.schedule.Data;

public class PresenterSecondDenominationFragment implements ContractSecondDenominationFragment.Presenter{
    private ContractSecondDenominationFragment.View mView;
    private ContractSecondDenominationFragment.Repository mRepository;


    PresenterSecondDenominationFragment(ContractSecondDenominationFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositorySecondDenominationFragment();
    }

    @Override
    public void getSchedule(String group) {
        List<Data> dataList = ConvertHelper.getInstance().scheduleWeek(mRepository.getSchedule(group));
        mView.setAdapter(dataList);
    }
}
