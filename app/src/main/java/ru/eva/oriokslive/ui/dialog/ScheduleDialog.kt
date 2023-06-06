package ru.eva.oriokslive.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import ru.eva.oriokslive.databinding.DialogSchedulerBinding
import ru.eva.oriokslive.ui.base.BaseDialog
import ru.eva.oriokslive.ui.entity.ScheduleItem

class ScheduleDialog(context: Context, private val item: ScheduleItem) :
    BaseDialog<DialogSchedulerBinding>(context) {

    override val bindingInflater: (LayoutInflater) -> DialogSchedulerBinding =
        DialogSchedulerBinding::inflate

    override fun setupUI() {
        binding.tvName.text = item.name
        binding.tvTeacher.text = item.teacher
        binding.tvTime.text = item.time
        binding.tvRoom.text = item.room
        binding.btnOk.setOnClickListener { dismiss() }
    }
}