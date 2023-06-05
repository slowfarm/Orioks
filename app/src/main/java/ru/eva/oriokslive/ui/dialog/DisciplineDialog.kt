package ru.eva.oriokslive.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.DialogEventsBinding
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.ui.base.BaseDialog

class DisciplineDialog(private val context: Context, private val discipline: Discipline) :
    BaseDialog<DialogEventsBinding>(context) {

    override val bindingInflater: (LayoutInflater) -> DialogEventsBinding =
        DialogEventsBinding::inflate

    override fun setupUI() {
        binding.tvForm.text = discipline.controlForm
        binding.tvDepartment.text = discipline.department
        binding.tvDate.text = if (discipline.examDate != "") {
            discipline.examDate
        } else {
            context.getString(R.string.date_not_assigned)
        }
        binding.tvTeacher.text = if (discipline.teachers.isNotEmpty()) {
            discipline.teachers.joinToString(",")
        } else {
            context.getString(R.string.teacher_not_assigned)
        }
        binding.btnOk.setOnClickListener { dismiss() }
    }
}
