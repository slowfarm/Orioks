package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
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

    fun bind(eventItem: EventItem) {
        with(binding) {
            tvName.text = eventItem.name
            tvGrade.text = eventItem.grade
            tvMaxGrade.text = eventItem.maxGrade
            tvWeek.text = eventItem.week
            pbScore.progress = eventItem.progress
            pbScore.setIndicatorColor(eventItem.color)
        }
    }
}