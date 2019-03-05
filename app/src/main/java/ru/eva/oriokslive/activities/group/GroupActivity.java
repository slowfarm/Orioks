package ru.eva.oriokslive.activities.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.eva.oriokslive.R;
import ru.eva.oriokslive.adapters.GroupAdapter;
import ru.eva.oriokslive.custom.RXSearch;
import ru.eva.oriokslive.interfaces.OnGroupItemClickListener;


public class GroupActivity extends AppCompatActivity implements ContractGroupActivity.View, OnGroupItemClickListener {

    private ContractGroupActivity.Presenter mPresenter;
    private GroupAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Группы");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter = new PresenterGroupActivity(this);
        mPresenter.getRecyclerView();
    }

    @Override
    public void setRecyclerView(List<String> groupList) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new GroupAdapter(groupList);
        adapter.setOnGroupItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gruop, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        RXSearch.fromSearchView(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> adapter.filterData(query));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v, String group) {
        setResult(RESULT_OK, new Intent().putExtra("group", group));
        finish();
    }
}
