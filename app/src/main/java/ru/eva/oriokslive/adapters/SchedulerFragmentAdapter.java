package ru.eva.oriokslive.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.helpers.DialogHelper;
import ru.eva.oriokslive.models.schedule.Data;

public class SchedulerFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Data> dataList;

    public SchedulerFragmentAdapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        switch (i) {
            case 1:
                return new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.schedulers_list_separator, parent, false));
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.scheduler_list_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Data item = dataList.get(position);
        switch (viewHolder.getItemViewType()) {
            case 0:
                ViewHolder holder = (ViewHolder)viewHolder;
                holder.name.setText(item.getClazz().getName());
                holder.audience.setText(item.getRoom().getName());
                holder.teacher.setText(item.getClazz().getTeacher());
                holder.timeStart.setText(item.getTime().getTimeFrom());
                holder.timeEnd.setText(item.getTime().getTimeTo());

                if(position == getItemCount()-1) {
                    setLayoutMarginBottom(holder.itemView, 16);
                } else {
                    setLayoutMarginBottom(holder.itemView, 0);
                }
                break;
            default:
                ViewHolder1 holder1 = (ViewHolder1)viewHolder;
                if(dataList.size() != 1 && item.getDayOfWeek() != null) {
                    holder1.dayOfWeek.setText(item.getDayOfWeek());
                }
                else {
                    holder1.dayOfWeek.setText("Нет занятий");
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(dataList.get(position).getDay() != null)
            return 0;
        else
            return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, audience, teacher, timeStart, timeEnd;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            audience = itemView.findViewById(R.id.audience);
            teacher = itemView.findViewById(R.id.teacher);
            timeStart = itemView.findViewById(R.id.time_start);
            timeEnd = itemView.findViewById(R.id.time_end);

            itemView.setOnClickListener(view->
                    DialogHelper
                            .getInstance()
                            .createDialog(dataList.get(getAdapterPosition()), view)
                            .show());
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        private final TextView dayOfWeek;
        ViewHolder1(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.day_of_week);
        }
    }

    private void setLayoutMarginBottom(View view, int margin) {
        ViewGroup.MarginLayoutParams params= new  ViewGroup.MarginLayoutParams(view.getLayoutParams());
        float dp = convertDpToPixel(view.getContext());
        params.setMargins(Math.round(dp), 0, Math.round(dp), margin);
        view.setLayoutParams(params);
    }

    private static float convertDpToPixel(Context context){
        return 4 * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
