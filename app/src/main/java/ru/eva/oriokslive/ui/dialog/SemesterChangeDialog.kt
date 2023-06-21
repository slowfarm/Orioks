package ru.eva.oriokslive.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import ru.eva.oriokslive.databinding.DialogSemesterChangeBinding
import ru.eva.oriokslive.network.entity.Semester
import ru.eva.oriokslive.ui.adapter.SemesterChangeAdapter
import ru.eva.oriokslive.ui.base.BaseDialog

class SemesterChangeDialog(
    context: Context,
    private val semester: Semester,
    private val listener: (Int) -> Unit,
) :
    BaseDialog<DialogSemesterChangeBinding>(context) {

    override val bindingInflater: (LayoutInflater) -> DialogSemesterChangeBinding =
        DialogSemesterChangeBinding::inflate

    override fun setupUI() {
        binding.rvSemester.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SemesterChangeAdapter(semester.list) {
                listener.invoke(it)
                dismiss()
            }
        }
        binding.btnCancel.setOnClickListener { dismiss() }
    }
}