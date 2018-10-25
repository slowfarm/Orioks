package ru.eva.oriokslive.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.models.orioks.Disciplines;
import ru.eva.oriokslive.models.schedule.Data;

public class DialogHelper {

    private static volatile DialogHelper instance;

    public static DialogHelper getInstance() {
        if (instance == null)
            instance = new DialogHelper();
        return instance;
    }

    public AlertDialog createDialog(Data data, View fromView) {
        Activity activity = (Activity) fromView.getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_scheduler, null);
        builder.setView(view).setPositiveButton("ОК", (dialog, id) -> dialog.dismiss());

        TextView name = view.findViewById(R.id.name);
        name.setText(data.getClazz().getName());

        TextView teacher = view.findViewById(R.id.teacher);
        teacher.setText(data.getClazz().getTeacher());

        TextView time = view.findViewById(R.id.time);
        time.setText(data.getTime().getTimeFrom() + "-" +data.getTime().getTimeTo());

        TextView audience = view.findViewById(R.id.audience);
        audience.setText(data.getRoom().getName());

        return builder.create();
    }

    public AlertDialog createDialog(Disciplines disciplines, Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_events, null);
        builder.setView(view).setPositiveButton("ОК", (dialog, id) -> dialog.dismiss());

        TextView form = view.findViewById(R.id.form);
        form.setText(disciplines.getControlForm());

        TextView department = view.findViewById(R.id.department);
        department.setText(disciplines.getDepartment());

        TextView time = view.findViewById(R.id.time);
        if(!disciplines.getExamDate().equals(""))
            time.setText(disciplines.getExamDate());
        else
            time.setText("Не назначена");

        TextView teacher = view.findViewById(R.id.teacher);
        StringBuilder teacherList = new StringBuilder();
        for(String teachers : disciplines.getTeachers()) {
            teacherList.append(teachers).append(", ");
        }
        if(teacherList.length()>0) {
            teacherList = new StringBuilder(teacherList.substring(0, teacherList.length() - 2));
            teacher.setText(teacherList.toString());
        } else
            teacher.setText("Не назначен");
        return builder.create();
    }
}
