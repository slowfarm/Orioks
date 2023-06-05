package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.eva.oriokslive.databinding.ListItemScheduleBinding
import ru.eva.oriokslive.databinding.ListItemScheduleSeparatorBinding
import ru.eva.oriokslive.network.entity.schedule.Data
import ru.eva.oriokslive.ui.entity.ScheduleItem

class ScheduleAdapter(private val schedule: List<ScheduleItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        1 -> ScheduleSeparatorViewHolder(
            ListItemScheduleSeparatorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        else -> ScheduleViewHolder(
            ListItemScheduleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = schedule.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = schedule[position]
        when (holder) {
            is ScheduleSeparatorViewHolder -> {
                holder.bind(item)
            }
            is ScheduleViewHolder -> holder.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int = if (schedule[position].day == null) 1 else 0
}

class ScheduleSeparatorViewHolder(private val binding: ListItemScheduleSeparatorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ScheduleItem) {
        binding.tvDayOfWeek.text = item.dayOfWeek ?: "Нет занятий"
    }
}

class ScheduleViewHolder(private val binding: ListItemScheduleBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ScheduleItem) {
        binding.tvName.text = item.clazz?.name
        binding.tvRoom.text = item.room?.name
        binding.tvTeacher.text = item.clazz?.teacher
        binding.timeStart.text = item.time?.timeFrom
        binding.timeEnd.text = item.time?.timeTo
    }
}

