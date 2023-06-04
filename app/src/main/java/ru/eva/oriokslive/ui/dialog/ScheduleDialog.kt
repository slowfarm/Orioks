package ru.eva.oriokslive.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import ru.eva.oriokslive.databinding.DialogSchedulerBinding
import ru.eva.oriokslive.network.entity.schedule.Data
import ru.eva.oriokslive.ui.base.BaseDialog

class ScheduleDialog(context: Context, private val data: Data) :
    BaseDialog<DialogSchedulerBinding>(context) {

    override val bindingInflater: (LayoutInflater) -> DialogSchedulerBinding =
        DialogSchedulerBinding::inflate

    override fun setupUI() {
        binding.tvName.text = data.clazz?.name
        binding.tvTeacher.text = data.clazz?.teacher
        binding.tvDate.text = data.time?.timeFrom + "-" + data.time?.timeTo
        binding.tvRoom.text = data.room?.name
        binding.btnOk.setOnClickListener { dismiss() }
    }
}