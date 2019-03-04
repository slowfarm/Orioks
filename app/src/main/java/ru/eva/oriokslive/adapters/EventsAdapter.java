package ru.eva.oriokslive.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.models.orioks.Event;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<Event> eventList;


    public EventsAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    public void addItems(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.events_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.value.setText(event.getCurrentGradeValue());
        holder.name.setText(event.getType());
        holder.valueFrom.setText(event.getMaxGradeValue());
        holder.progressBar.setProgress(event.getProgress());
        holder.progressBar.setForegroundStrokeColor(event.getColor());
        holder.week.setText(eventList.get(position).getWeek());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, value, week, valueFrom;
        private final CircularProgressBar progressBar;

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