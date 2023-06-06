package ru.eva.oriokslive.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.DialogEventsBinding
import ru.eva.oriokslive.network.entity.orioks.Debt
import ru.eva.oriokslive.ui.base.BaseDialog

class DebtDialog(private val context: Context, private val debt: Debt) :
    BaseDialog<DialogEventsBinding>(context) {

    override val bindingInflater: (LayoutInflater) -> DialogEventsBinding =
        DialogEventsBinding::inflate

    override fun setupUI() {
        binding.tvForm.text = context.getString(R.string.form, debt.formControl)
        binding.tvDepartment.text = context.getString(R.string.department, debt.institute)
        binding.tvDate.text = if (debt.deadline != "") {
            context.getString(R.string.exam_date, debt.deadline)
        } else {
            context.getString(R.string.date_not_assigned)
        }
        binding.tvTeacher.text = if (debt.teachers.isNotEmpty()) {
            context.getString(R.string.teacher, debt.teachers.joinToString(","))
        } else {
            context.getString(R.string.teacher_not_assigned)
        }
        binding.btnOk.setOnClickListener { dismiss() }
    }
}
