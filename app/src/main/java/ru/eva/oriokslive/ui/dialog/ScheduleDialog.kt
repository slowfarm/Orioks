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
        binding.tvName.text = item.clazz?.name
        binding.tvTeacher.text = item.clazz?.teacher
        binding.tvDate.text =
            context.getString(R.string.date_range, item.time?.timeFrom, item.time?.timeTo)
        binding.tvRoom.text = item.room?.name
        binding.btnOk.setOnClickListener { dismiss() }
    }
}