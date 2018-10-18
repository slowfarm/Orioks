package ru.eva.oriokslive.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.helpers.DialogHelper;
import ru.eva.oriokslive.models.schedule.Data;

class SchedulerFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Data> dataList;

    SchedulerFragmentAdapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        switch (i) {
            case 0:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.scheduler_list_item, parent, false));
            case 1:
                return new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.schedulers_list_separator, parent, false));
                default:
                    return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.scheduler_list_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 0:
                ViewHolder holder = (ViewHolder)viewHolder;
                holder.name.setText(dataList.get(position).getClazz().getName());
                holder.audience.setText(dataList.get(position).getRoom().getName());
                holder.teacher.setText(dataList.get(position).getClazz().getTeacher());
                holder.timeStart.setText(dataList.get(position).getTime().getTimeFrom());
                holder.timeEnd.setText(dataList.get(position).getTime().getTimeTo());
                break;
            case 1:
                ViewHolder1 holder1 = (ViewHolder1)viewHolder;
                if(dataList.size() != 1)
                    holder1.dayOfWeek.setText(getDayOfWeek(position));
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

        private final TextView name;
        private final TextView audience;
        private final TextView teacher;
        private final TextView timeStart;
        private final TextView timeEnd;

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

    private String getDayOfWeek(int position) {
        switch (dataList.get(position+1).getDay()) {
            case 1:
                return "Понедельник";
            case 2:
                return "Вторник";
            case 3:
                return "Среда";
            case 4:
                return "Четверг";
            case 5:
                return "Пятница";
            case 6:
                return "Суббота";
            case 7:
                return "Воскресенье";
            default:
                return "Понедельник";
        }
    }


}
