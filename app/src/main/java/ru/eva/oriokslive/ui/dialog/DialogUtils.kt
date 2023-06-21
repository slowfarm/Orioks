package ru.eva.oriokslive.ui.dialog

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.DialogStudentBinding
import ru.eva.oriokslive.network.entity.orioks.Student

fun showDescriptionDialog(context: Context, student: Student) {
    BottomSheetDialog(context).apply {
        val binding = DialogStudentBinding.inflate(layoutInflater)
        with(binding) {
            tvCourse.text = context.getString(R.string.course, student.course)
            tvId.text = context.getString(R.string.id, student.recordBookId)
            tvSemester.text = context.getString(R.string.semester, student.semester)
            tvProfile.text = context.getString(R.string.training_profile, student.studyProfile)
            tvYear.text = context.getString(R.string.year, student.year)
        }
        setContentView(binding.root)
        show()
    }
}
