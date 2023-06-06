package ru.eva.oriokslive.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.DialogSchedulerBinding
import ru.eva.oriokslive.ui.base.BaseDialog
import ru.eva.oriokslive.ui.entity.ScheduleItem

class ScheduleDialog(private val context: Context, private val item: ScheduleItem) :
    BaseDialog<DialogSchedulerBinding>(context) {

    override val bindingInflater: (LayoutInflater) -> DialogSchedulerBinding =
        DialogSchedulerBinding::inflate

    override fun setupUI() {
        binding.tvName.text = context.getString(R.string.affair, item.name)
        binding.tvTeacher.text = context.getString(R.string.teacher, item.teacher)
        binding.tvTime.text = context.getString(R.string.time, item.time)
        binding.tvRoom.text = context.getString(R.string.room, item.room)
        binding.btnOk.setOnClickListener { dismiss() }
    }
}