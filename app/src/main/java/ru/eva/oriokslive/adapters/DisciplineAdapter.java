package ru.eva.oriokslive.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.activities.Events.EventsActivity;
import ru.eva.oriokslive.models.orioks.Disciplines;

public class DisciplineAdapter extends RecyclerView.Adapter<DisciplineAdapter.ViewHolder> {

    private List<Disciplines> disciplinesList;


    public DisciplineAdapter(List<Disciplines> disciplinesList) {
        this.disciplinesList = disciplinesList;
    }

    public void addItems(List<Disciplines> disciplinesList) {
        this.disciplinesList = disciplinesList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.discipline_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        double progress = disciplinesList.get(position).getCurrentGrade() / disciplinesList.get(position).getMaxGrade() * 100;
        holder.name.setText(disciplinesList.get(position).getName());
        if(disciplinesList.get(position).getCurrentGrade() != -1.0)
            holder.value.setText(String.valueOf(disciplinesList.get(position).getCurrentGrade()));
        else
            holder.value.setText("-");

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
    }

    @Override
    public int getItemCount() {
        return disciplinesList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final CircularProgressBar progressBar;
        private final TextView value;
        private final TextView valueFrom;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_view);
            progressBar = itemView.findViewById(R.id.progress_bar);
            value = itemView.findViewById(R.id.value);
            valueFrom = itemView.findViewById(R.id.value_from);
            itemView.setOnClickListener(view-> view.getContext()
                    .startActivity(
                            new Intent(view.getContext(), EventsActivity.class)
                                    .putExtra("id", disciplinesList.get(getAdapterPosition()).getId())));
        }
    }
}