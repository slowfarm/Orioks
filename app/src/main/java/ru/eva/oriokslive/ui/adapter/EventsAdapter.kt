package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.eva.oriokslive.databinding.ListItemEventBinding
import ru.eva.oriokslive.ui.entity.EventItem

class EventsAdapter(private val events: List<EventItem>) :
    RecyclerView.Adapter<EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventsViewHolder(
        ListItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size
}

class EventsViewHolder(private val binding: ListItemEventBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EventItem) {
        with(binding) {
            tvName.text = item.name
            tvGrade.text = item.grade
            tvMaxGrade.text = item.maxGrade
            tvWeek.text = item.week
            pbScore.progress = item.progress
            pbScore.setIndicatorColor(ContextCompat.getColor(binding.root.context, item.color))
        }
    }
}