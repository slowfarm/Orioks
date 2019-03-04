package ru.eva.oriokslive.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.activities.event.EventActivity;
import ru.eva.oriokslive.models.orioks.Discipline;

public class DisciplineAdapter extends RecyclerView.Adapter<DisciplineAdapter.ViewHolder> {

    private List<Discipline> disciplineList;


    public DisciplineAdapter(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }

    public void addItems(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.discipline_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Discipline discipline = disciplineList.get(position);
        holder.name.setText(discipline.getName());
        holder.value.setText(discipline.getCurrentGradeValue());
        holder.valueFrom.setText(discipline.getMaxGradeValue());
        holder.formControl.setText(discipline.getControlForm());
        holder.progressBar.setProgress(discipline.getProgress());
        holder.progressBar.setForegroundStrokeColor(discipline.getColor());

    }

    @Override
    public int getItemCount() {
        return disciplineList.size();
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
                                    .putExtra("id", disciplineList.get(getAdapterPosition()).getId())));
        }
    }
}