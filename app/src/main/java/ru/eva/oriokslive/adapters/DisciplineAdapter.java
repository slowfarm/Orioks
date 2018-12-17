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
import ru.eva.oriokslive.activities.event.EventActivity;
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
        Disciplines discipline =disciplinesList.get(position);
        double progress = discipline.getCurrentGrade() / discipline.getMaxGrade() * 100;

        holder.name.setText(discipline.getName());
        if(discipline.getCurrentGrade() != -1.0)
            holder.value.setText(String.valueOf(discipline.getCurrentGrade()));
        else
            holder.value.setText("-");

        holder.valueFrom.setText(String.valueOf(discipline.getMaxGrade()));

        holder.formControl.setText(discipline.getControlForm());

        if(progress >= 0)
            holder.progressBar.setProgress((int) progress);
        else
            holder.progressBar.setProgress(0);
        if(progress < 50) {
            holder.progressBar.setForegroundStrokeColor(Color.parseColor("#FF6D00"));
        } else if(progress < 70) {
            holder.progressBar.setForegroundStrokeColor(Color.parseColor("#FFD600"));
        } else if(progress < 85) {
            holder.progressBar.setForegroundStrokeColor(Color.parseColor("#64DD17"));
        } else {
            holder.progressBar.setForegroundStrokeColor(Color.parseColor("#00C853"));
        }
    }

    @Override
    public int getItemCount() {
        return disciplinesList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name,valueFrom, value, formControl;
        private final CircularProgressBar progressBar;


        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.discipline);
            progressBar = itemView.findViewById(R.id.progress_bar);
            value = itemView.findViewById(R.id.value);
            valueFrom = itemView.findViewById(R.id.value_from);
            formControl = itemView.findViewById(R.id.form_control);
            itemView.setOnClickListener(view-> view.getContext()
                    .startActivity(
                            new Intent(view.getContext(), EventActivity.class)
                                    .putExtra("id", disciplinesList.get(getAdapterPosition()).getId())));
        }
    }
}