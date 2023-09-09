package ru.eva.oriokslive.ui.dialog

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.DialogStudentBinding
import ru.eva.oriokslive.network.entity.orioks.Student

fun showProfileDialog(context: Context, student: Student) {
    BottomSheetDialog(context).apply {
        val binding = DialogStudentBinding.inflate(layoutInflater)
        with(binding) {
            tvDepartment2.text = context.getString(R.string.department, student.department)
            tvProfile.text = context.getString(R.string.training_profile, student.studyProfile)
            tvCourse.text = context.getString(R.string.course, student.course)
            tvSemester.text = context.getString(R.string.semester, student.semester)
            tvYear.text = context.getString(R.string.year, student.year)
            tvId.text = context.getString(R.string.id, student.recordBookId)
        }
        setContentView(binding.root)
        show()
    }
}
