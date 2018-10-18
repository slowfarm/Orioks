package ru.eva.oriokslive.fragmens.Student;

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
import ru.eva.oriokslive.activities.Registration.RegistrationActivity;
import ru.eva.oriokslive.models.orioks.Student;

public class StudentFragment extends Fragment implements Contract.View {
    private Contract.Presenter mPresenter;
    private TextView name;
    private TextView group;
    private TextView department;
    private TextView course;
    private TextView id;
    private TextView semester;
    private TextView direction;
    private TextView profile;
    private TextView year;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student, container, false);
        name = view.findViewById(R.id.name);
        group = view.findViewById(R.id.group);
        department = view.findViewById(R.id.department);
        course = view.findViewById(R.id.course);
        id = view.findViewById(R.id.id);
        semester = view.findViewById(R.id.semester);
        direction = view.findViewById(R.id.direction);
        profile = view.findViewById(R.id.profile);
        year = view.findViewById(R.id.year);
        Button button = view.findViewById(R.id.exit);

        mPresenter = new Presenter(this);
        mPresenter.getStudent();

        button.setOnClickListener(v-> mPresenter.onButtonWasClicked());
        return view;
    }

    @Override
    public void setStudent(Student student) {
        name.setText(student.getFullName());
        group.setText(student.getGroup());
        department.setText(student.getDepartment());
        course.setText(student.getCourse());
        id.setText(student.getRecordBookId());
        semester.setText(student.getSemester());
        direction.setText(student.getStudyDirection());
        profile.setText(student.getStudyProfile());
        year.setText(student.getYear());
    }

    @Override
    public void finishActivity() {
        ((Activity)view.getContext()).finishAffinity();
        view.getContext().startActivity(new Intent(view.getContext(), RegistrationActivity.class));
    }
}
