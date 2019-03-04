package ru.eva.oriokslive.activities.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.adapters.SchedulerFragmentPagerAdapter;

public class SchedulerActivity extends AppCompatActivity implements ContractSchedulerActivity.View {

    private ViewPager viewPager;

    private ContractSchedulerActivity.Presenter mPresenter;
    private SchedulerFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        String group = getIntent().getStringExtra("group");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(group);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        adapter = new SchedulerFragmentPagerAdapter(getSupportFragmentManager());
        adapter.setGroup(group);

        mPresenter = new PresenterSchedulerActivity(this);
        mPresenter.setPagerAdapter(group);
    }

    @Override
    public void setPagerAdapter() {
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
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
                default:
                    onBackPressed();
                    return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
