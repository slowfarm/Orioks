package ru.eva.oriokslive.activities.Main;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.fragmens.Main.MainFragment;
import ru.eva.oriokslive.fragmens.Scheduler.SchedulerFragment;
import ru.eva.oriokslive.fragmens.Student.StudentFragment;
import ru.eva.oriokslive.interfaces.OnViewPagerChangeListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OnViewPagerChangeListener, ContractMainActivity.View {

    private MainFragment mainFragment;
    private StudentFragment studentFragment;
    private SchedulerFragment schedulerFragment;
    private FragmentTransaction fTrans;

    private TextView usernameText;
    private TextView groupText;
    private Toolbar toolbar;
    private Menu menu;
    private TextView week;
    private TextView value;
    private CircularProgressBar progressBar;

    private ContractMainActivity.Presenter mPresenter;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Обучение");
        setSupportActionBar(toolbar);

        mainFragment = new MainFragment();
        studentFragment = new StudentFragment();
        schedulerFragment = new SchedulerFragment();
        schedulerFragment.setOnViewPagerChangeListener(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        usernameText =  navigationView.getHeaderView(0).findViewById(R.id.username_text);
        groupText = navigationView.getHeaderView(0).findViewById(R.id.group_text);
        progressBar = navigationView.getHeaderView(0).findViewById(R.id.progress_bar);
        week = navigationView.getHeaderView(0).findViewById(R.id.week);
        value = navigationView.getHeaderView(0).findViewById(R.id.value);

        mPresenter = new PresenterMainActivity(this);
        mPresenter.setCurrentWeek();
        mPresenter.getStudent();
        mPresenter.setStudent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.frame_layout, mainFragment).commit();
        navigationView.setCheckedItem(R.id.nav_main);
        mPresenter.onResume(menu);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_date:
                mPresenter.setViewPagerToPosition();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fTrans = getFragmentManager().beginTransaction();
        menu.getItem(0).setVisible(false);
        switch (item.getItemId()) {
            case R.id.nav_main:
                toolbar.setTitle("Обучение");
                fTrans.replace(R.id.frame_layout, mainFragment);
                break;
            case  R.id.nav_scheduler:
                toolbar.setTitle("Сегодня");
                fTrans.replace(R.id.frame_layout, schedulerFragment);
                menu.getItem(0).setVisible(true);
                break;
            case R.id.nav_student:
                toolbar.setTitle("О студенте");
                fTrans.replace(R.id.frame_layout, studentFragment);
                break;
        }
        fTrans.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onChange(int position) {
       mPresenter.getToolbarTitle(position);
    }

    @Override
    public void showCurrentWeek(String currentWeek, float progress, String currentValue) {
        progressBar.setProgress(progress);
        week.setText(currentWeek);
        value.setText(currentValue);
    }

    @Override
    public void setViewPagerToPosition(int position) {
        schedulerFragment.setViewPagerToPosition(position);
    }

    @Override
    public void setStudent(String name, String group) {
        usernameText.setText(name);
        groupText.setText(group);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void hideMenu() {
        menu.getItem(0).setVisible(false);
    }
}
