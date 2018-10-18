package ru.eva.oriokslive.activities.Events;

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
import ru.eva.oriokslive.models.orioks.Disciplines;
import ru.eva.oriokslive.models.orioks.Events;


public class EventsActivity extends AppCompatActivity implements Contract.View {

    private EventsAdapter adapter;
    private Contract.Presenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Intent intent = getIntent();
        String intExtraName = "id";
        int id = intent.getIntExtra(intExtraName, 0);

        mPresenter = new Presenter(this, id);
        mPresenter.getToolbarTitle();
        mPresenter.getRecyclerView();
        mPresenter.getEvents(this);
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
                mPresenter.optionsItemSelected();
                break;
            default:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setRecyclerView(List<Events> data) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new EventsAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showDialog(Disciplines discipline, DialogHelper dialog) {
        dialog.createDialog(discipline, this).show();
    }

    @Override
    public void setToolbarTitle(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void addRecyclerViewItems(List<Events> data) {
        adapter.addItems(data);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
