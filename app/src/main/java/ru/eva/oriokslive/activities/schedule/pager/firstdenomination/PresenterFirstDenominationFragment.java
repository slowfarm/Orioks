package ru.eva.oriokslive.activities.schedule.pager.firstdenomination;

import java.util.List;

import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.models.miet.schedule.Data;

public class PresenterFirstDenominationFragment implements ContractFirstDenominationFragment.Presenter{
    private ContractFirstDenominationFragment.View mView;
    private ContractFirstDenominationFragment.Repository mRepository;


    PresenterFirstDenominationFragment(ContractFirstDenominationFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositoryFirstDenominationFragment();
    }

    @Override
    public void getSchedule(String group) {
        List<Data> dataList = ConvertHelper.getInstance().scheduleWeek(mRepository.getSchedule(group));
        mView.setAdapter(dataList);
    }
}
