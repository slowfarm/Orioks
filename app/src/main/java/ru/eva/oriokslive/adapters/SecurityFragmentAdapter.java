package ru.eva.oriokslive.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.interfaces.OnDeleteButtonClickListener;
import ru.eva.oriokslive.models.orioks.Security;

public class SecurityFragmentAdapter extends RecyclerView.Adapter<SecurityFragmentAdapter.ViewHolder> {
    private List<Security> tokenList;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();
    private OnDeleteButtonClickListener onDeleteButtonClickListener;


    public SecurityFragmentAdapter(List<Security> tokenList) {
        this.tokenList = tokenList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.security_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecurityFragmentAdapter.ViewHolder holder, int position) {
        binderHelper.bind(holder.swipeLayout, tokenList.get(position).getToken());
        if(tokenList.get(position).getUserAgent().split("\\s").length == 3) {
            holder.application.setText(getApplicationName(tokenList.get(position).getUserAgent()));
            holder.device.setText(getDeviceName(tokenList.get(position).getUserAgent()));
        }
        else {
            holder.application.setText(tokenList.get(position).getUserAgent());
            holder.deviceLayout.setVisibility(View.GONE);
        }
        holder.date.setText(dataParser(tokenList.get(position).getLastUsed()));

    }

    @Override
    public int getItemCount() {
        return tokenList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SwipeRevealLayout swipeLayout;
        private View deleteLayout;
        private TextView date, device, application;
        private LinearLayout deviceLayout;

        ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.swipe_layout);
            deleteLayout = itemView.findViewById(R.id.delete_layout);
            date = itemView.findViewById(R.id.date);
            device = itemView.findViewById(R.id.device);
            application = itemView.findViewById(R.id.application);
            deviceLayout = itemView.findViewById(R.id.device_layout);
            deleteLayout.setOnClickListener(v ->  {
                onDeleteButtonClickListener.onClick(tokenList.get(getAdapterPosition()), getAdapterPosition());
                swipeLayout.close(true);
            });
        }
    }

    public void setOnDeleteButtonClickListener(OnDeleteButtonClickListener onDeleteButtonClickListener) {
        this.onDeleteButtonClickListener = onDeleteButtonClickListener;
    }

    private String dataParser(String inputDate) {
        String oldPattern = "yyyy-MM-dd'T'HH:mm";
        String newPattern = "HH:mm dd.MM.yyy";
        SimpleDateFormat sdf = new SimpleDateFormat(oldPattern, Locale.getDefault());
        Date date = new Date();
        Date dateNow = new Date();
        try {
            date = sdf.parse(inputDate);
            sdf.applyPattern(newPattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int hour = 1000 * 60 * 60;
        int day = hour * 24;
        int week = day* 7;
        long diff = dateNow.getTime() - date.getTime();
        if(diff < hour)
            return  "Недавно";
        if(diff < day)
            return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS) + " часов назад";
        if(diff < week)
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + " дней назад";
        return sdf.format(date);
    }

    private String getApplicationName(String text) {
        String[] agent = text.split("\\s");
        return agent[0];
    }

    private String getDeviceName(String text) {
        String[] agent = text.split("\\s");
        return agent[1] + " " + agent[2];
    }
}