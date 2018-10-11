package ru.eva.oriokslive.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.adapters.EventsAdapter;
import ru.eva.oriokslive.helpers.DialogHelper;
import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnEventsRecieved;
import ru.eva.oriokslive.models.orioks.Disciplines;
import ru.eva.oriokslive.models.orioks.Events;


public class EventsActivity extends AppCompatActivity implements OnEventsRecieved {

    RecyclerView recyclerView;
    EventsAdapter adapter;
    Toolbar toolbar;
    Disciplines disciplines;
    int id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        disciplines = StorageHelper.getInstance().getDiscipline(id);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(disciplines.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Events> eventsList = StorageHelper.getInstance().getEventsList(id);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new EventsAdapter(eventsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        RetrofitHelper.getInstance().setOnEventsRecieved(this);
        RetrofitHelper.getInstance().getEvents(StorageHelper.getInstance().getAccessToken(this), id);
    }

    @Override
    public void onResponse(List<Events> eventsList) {
        if(StorageHelper.getInstance().getEventsList(id).size() != eventsList.size()) {
            adapter.addItems(eventsList);
            for (Events event : eventsList)
                event.setId(id);
            StorageHelper.getInstance().setEvents(eventsList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.events, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view:
                DialogHelper
                        .getInstance()
                        .createDialog(disciplines, this)
                        .show();
                break;
            default:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
