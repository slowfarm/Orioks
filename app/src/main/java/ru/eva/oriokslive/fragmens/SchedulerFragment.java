package ru.eva.oriokslive.fragmens;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.adapters.SchedulerFragmentPagerAdapter;
import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnSchedulersReceived;
import ru.eva.oriokslive.interfaces.OnViewPagerChangeListener;
import ru.eva.oriokslive.models.schedule.Data;
import ru.eva.oriokslive.models.schedule.Schedulers;

public class SchedulerFragment extends Fragment implements OnSchedulersReceived {

    private SchedulerFragmentPagerAdapter adapter;
    private OnViewPagerChangeListener onViewPagerChangeListener;
    private ViewPager viewPager;
    private View view;
    private int currentDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scheduler, container, false);

        int currentWeek = StorageHelper.getInstance().getCurrentWeek(view.getContext());
        currentDay = (currentWeek-1)%4;
        List<Data> dataList = StorageHelper.getInstance().getSchedulersDataCurrentWeek(currentWeek);

        adapter = new SchedulerFragmentPagerAdapter(view.getContext(), currentDay);
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {}
            @Override
            public void onPageSelected(int i) {
                onViewPagerChangeListener.onChange(i);
                Log.d("tagd", "onChange");
            }
            @Override
            public void onPageScrollStateChanged(int i) { }
        });


        if(dataList.size() == 0) {
            RetrofitHelper.getInstance().setOnSchedulersReceived(this);
            RetrofitHelper.getInstance().getSchedulers(StorageHelper.getInstance().getStudent().getGroup());
        }
        return view;
    }

    @Override
    public void onResponse(Schedulers schedulers) {
        StorageHelper.getInstance().setSchedulers(schedulers);

        adapter = new SchedulerFragmentPagerAdapter(view.getContext(), currentDay);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d("tagD", t.getMessage());
    }

    public void setOnViewPagerChangeListener(OnViewPagerChangeListener onViewPagerChangeListener) {
        this.onViewPagerChangeListener = onViewPagerChangeListener;
    }

    public void setViewPagerToPosition(int position) {
        position+=2;
        viewPager.setCurrentItem(position);
    }
}
