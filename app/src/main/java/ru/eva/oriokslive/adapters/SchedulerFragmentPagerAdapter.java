package ru.eva.oriokslive.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.models.schedule.Data;

public class SchedulerFragmentPagerAdapter extends PagerAdapter {

    private Context context;
    private int currentWeek;

    public SchedulerFragmentPagerAdapter(Context context, int currentWeek) {
        this.context = context;
        this.currentWeek = currentWeek;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.scheduler_pager_item, container, false);
        Log.d("dayOf", getDayOfWeek()+"");
        List<Data> dataList;
        switch (position) {
            case 0:
                dataList = StorageHelper.getInstance().getSchedulersDataCurrentDay(currentWeek, getDayOfWeek());
                break;
            case 1:
                dataList = StorageHelper.getInstance().getSchedulersDataCurrentDay(currentWeek, getNextDayOfWeek());
                break;
                default:
                    dataList = StorageHelper.getInstance().getSchedulersDataCurrentWeek(position-2);
                    dataList.add(0, new Data());
                    int listSize = dataList.size();
                    for(int i = 1; i< listSize; i++) {
                        if(!dataList.get(i).getDay().equals(dataList.get(i+1).getDay())) {
                            dataList.add(i+1, new Data());
                            i++;
                        }
                    }
                    break;
        }
        SchedulerFragmentAdapter adapter = new SchedulerFragmentAdapter(dataList);
        RecyclerView recyclerView = viewItem.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        container.addView(viewItem);
        return viewItem;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private int getDayOfWeek() {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    private int getNextDayOfWeek() {
        if(getDayOfWeek() == 6)
            return 1;
        else
            return getDayOfWeek()+1;
    }
}
