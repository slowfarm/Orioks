package ru.eva.oriokslive.fragmens;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.activities.RegistrationActivity;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.models.orioks.Student;

public class StudentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        TextView name = view.findViewById(R.id.name);
        TextView group = view.findViewById(R.id.group);
        TextView department = view.findViewById(R.id.department);
        TextView course = view.findViewById(R.id.course);
        TextView id = view.findViewById(R.id.id);
        TextView semester = view.findViewById(R.id.semester);
        TextView direction = view.findViewById(R.id.direction);
        TextView profile = view.findViewById(R.id.profile);
        TextView year = view.findViewById(R.id.year);
        Button button = view.findViewById(R.id.exit);

        Student student = StorageHelper.getInstance().getStudent();
        if(student != null) {
            name.setText(student.getFullName());
            group.setText(student.getGroup());
            department.setText(student.getDepartment());
            course.setText(student.getCourse()+"");
            id.setText(student.getRecordBookId());
            semester.setText(student.getSemester()+"");
            direction.setText(student.getStudyDirection());
            profile.setText(student.getStudyProfile());
            year.setText(student.getYear());
        }
        button.setOnClickListener(v->{
            StorageHelper.getInstance().clearTables(v.getContext());
            ((Activity)v.getContext()).finishAffinity();
            v.getContext().startActivity(new Intent(v.getContext(), RegistrationActivity.class));
        });
        return view;
    }
}
