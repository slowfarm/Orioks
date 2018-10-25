package ru.eva.oriokslive.fragmens.Scheduler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.adapters.SchedulerFragmentPagerAdapter;
import ru.eva.oriokslive.interfaces.OnViewPagerChangeListener;

public class SchedulerFragment extends Fragment implements ContractSchedulerFragment.View {

    private OnViewPagerChangeListener onViewPagerChangeListener;
    private ViewPager viewPager;
    private View view;

    ContractSchedulerFragment.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scheduler, container, false);
        setHasOptionsMenu(true);

        mPresenter = new PresenterSchedulerFragment(this);
        mPresenter.getCurrentDay();

        mPresenter.getSchedule();
        return view;
    }

    public void setOnViewPagerChangeListener(OnViewPagerChangeListener onViewPagerChangeListener) {
        this.onViewPagerChangeListener = onViewPagerChangeListener;
    }

    @Override
    public void setPagerAdapter(int currentDay) {
        SchedulerFragmentPagerAdapter adapter = new SchedulerFragmentPagerAdapter(view.getContext(), currentDay);
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {}
            @Override
            public void onPageSelected(int i) {
                mPresenter.onPageChange(i, onViewPagerChangeListener);
            }
            @Override
            public void onPageScrollStateChanged(int i) { }
        });
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageChange(int i) {
        onViewPagerChangeListener.onChange(i);
    }

    @Override
    public void setViewPagerToPosition(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_date:
                mPresenter.setViewPagerToPosition();
                return true;
            case R.id.action_refresh:
                mPresenter.refreshSchedule();
                return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
