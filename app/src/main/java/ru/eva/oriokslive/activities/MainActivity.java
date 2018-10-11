package ru.eva.oriokslive.activities;

import android.app.Fragment;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.fragmens.MainFragment;
import ru.eva.oriokslive.fragmens.SchedulerFragment;
import ru.eva.oriokslive.fragmens.StudentFragment;
import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnStudentRecieved;
import ru.eva.oriokslive.interfaces.OnViewPagerChangeListener;
import ru.eva.oriokslive.models.orioks.Student;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OnStudentRecieved,
        OnViewPagerChangeListener {

    MainFragment mainFragment;
    StudentFragment studentFragment;
    SchedulerFragment schedulerFragment;
    FragmentTransaction fTrans;

    TextView usernameText;
    TextView groupText;
    Toolbar toolbar;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
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

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        usernameText =  navigationView.getHeaderView(0).findViewById(R.id.username_text);
        groupText = navigationView.getHeaderView(0).findViewById(R.id.group_text);
        Student student = StorageHelper.getInstance().getStudent();
        if(student != null) {
            usernameText.setText(student.getFullName());
            groupText.setText(student.getGroup());
        }
        CircularProgressBar progressBar = navigationView.getHeaderView(0).findViewById(R.id.progress_bar);

        int currentWeek = getCurrentWeek();
        StorageHelper.getInstance().setCurrentWeek(this, currentWeek);

        progressBar.setProgress((float)currentWeek/18*100);
        TextView week = navigationView.getHeaderView(0).findViewById(R.id.week);
        week.setText(String.valueOf(currentWeek));
        TextView value = navigationView.getHeaderView(0).findViewById(R.id.value);
        value.setText(getCurrentValue(currentWeek));

        fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.frame_layout, mainFragment).commit();
        toolbar.setTitle("Главная");

        RetrofitHelper.getInstance().setOnStudentReceived(this);
        RetrofitHelper.getInstance().getStudent(StorageHelper.getInstance().getAccessToken(this));
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_date:
                schedulerFragment.setViewPagerToPosition((getCurrentWeek()-1)%4);
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
                toolbar.setTitle("Главная");
                fTrans.replace(R.id.frame_layout, mainFragment);
                break;
            case R.id.nav_student:
                toolbar.setTitle("О студенте");
                fTrans.replace(R.id.frame_layout, studentFragment);
                break;
            case  R.id.nav_scheduler:
                toolbar.setTitle("Сегодня");
                fTrans.replace(R.id.frame_layout, schedulerFragment);
                menu.getItem(0).setVisible(true);
                break;
        }
        fTrans.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResponse(Student student) {
        if(student != null) {
            if(student.getError() != null) {
                Toast.makeText(this, student.getError(), Toast.LENGTH_SHORT).show();
            }
            else {
                usernameText.setText(student.getFullName());
                groupText.setText(student.getGroup());
                StorageHelper.getInstance().setStudent(student);
            }
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }


    private int getCurrentWeek() {
        String format = "yyyyMMdd";

        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        String input = cal.get(Calendar.YEAR)+"0901";
        Date date = null;
        try {
            date = df.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        int startWeek = cal.get(Calendar.WEEK_OF_YEAR);
        cal.setTime(new Date());
        int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
        int week = currentWeek - startWeek + 1;
        return week;
    }

    private String getCurrentValue(int week) {
        String value;
        switch (week % 4) {
            case 0:
                value = "2 Знаменатель";
                break;
            case 1:
                value = "1 Числитель";
                break;
            case 2:
                value = "1 Знаменатель";
                break;
            case 3:
                value = "2 Числитель";
                break;
            default:
                value = "1 Числитель";
                break;
        }
        return  value;
    }

    @Override
    public void onChange(int position) {
        switch (position) {
            case 0:
                toolbar.setTitle("Сегодня");
                break;
            case 1:
                toolbar.setTitle("Завтра");
                break;
            case 2:
                toolbar.setTitle("1 Числитель");
                break;
            case 3:
                toolbar.setTitle("1 Знаменатель");
                break;
            case 4:
                toolbar.setTitle("2 Числитель");
                break;
            case 5:
                toolbar.setTitle("2 Знаменатель");
                break;
        }
    }
}
