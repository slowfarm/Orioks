package ru.eva.oriokslive.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.models.orioks.Events;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<Events> disciplinesList;


    public EventsAdapter(List<Events> disciplinesList) {
        this.disciplinesList = disciplinesList;
    }

    public void addItems(List<Events> disciplinesList) {
        this.disciplinesList = disciplinesList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.events_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        double progress = 0;
        if(disciplinesList.get(position).getCurrentGrade() != null) {
            progress = disciplinesList.get(position).getCurrentGrade() / disciplinesList.get(position).getMaxGrade() * 100;
            if (disciplinesList.get(position).getCurrentGrade() != -1.0)
                holder.value.setText(String.valueOf(disciplinesList.get(position).getCurrentGrade()));
            else
                holder.value.setText("Н");
        } else {
            holder.value.setText("-");
        }

        holder.name.setText(disciplinesList.get(position).getType());
        holder.valueFrom.setText(String.valueOf(disciplinesList.get(position).getMaxGrade()));

        if(progress >= 0)
            holder.progressBar.setProgress((int) progress);
        else
            holder.progressBar.setProgress(0);

        if(progress < 50) {
            holder.progressBar.setForegroundStrokeColor(Color.parseColor("#FF7535"));
        } else if(progress < 70) {
            holder.progressBar.setForegroundStrokeColor(Color.parseColor("#F1C40F"));
        } else if(progress < 85) {
            holder.progressBar.setForegroundStrokeColor(Color.parseColor("#9ACB31"));
        } else {
            holder.progressBar.setForegroundStrokeColor(Color.parseColor("#28D002"));
        }

        holder.week.setText(disciplinesList.get(position).getWeek());
    }

    @Override
    public int getItemCount() {
        return disciplinesList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final CircularProgressBar progressBar;
        private final TextView value;
        private final TextView week;
        private final TextView valueFrom;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            progressBar = itemView.findViewById(R.id.progress_bar);
            value = itemView.findViewById(R.id.value);
            week = itemView.findViewById(R.id.week);
            valueFrom = itemView.findViewById(R.id.value_from);
        }
    }
}