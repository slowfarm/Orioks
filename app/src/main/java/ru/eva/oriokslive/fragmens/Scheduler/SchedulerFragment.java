package ru.eva.oriokslive.fragmens.Scheduler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
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

public class SchedulerFragment extends Fragment implements ContractSchedulerFragment.View {

    private ViewPager viewPager;
    private View view;

    private ContractSchedulerFragment.Presenter mPresenter;
    private FragmentManager fragmentManager;
    private SchedulerFragmentPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scheduler, container, false);
        setHasOptionsMenu(true);
        viewPager = view.findViewById(R.id.view_pager);
        TabLayout tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        adapter = new SchedulerFragmentPagerAdapter(fragmentManager);
        mPresenter = new PresenterSchedulerFragment(this);
        mPresenter.setPagerAdapter();
        return view;
    }

    @Override
    public void setPagerAdapter() {
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setViewPagerToPosition(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_date:
                mPresenter.setViewPagerToCurrentWeek();
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

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
}
