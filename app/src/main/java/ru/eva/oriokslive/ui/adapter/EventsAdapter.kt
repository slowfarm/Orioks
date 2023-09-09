package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.eva.oriokslive.databinding.ListItemEventBinding
import ru.eva.oriokslive.ui.entity.EventItem
import ru.eva.oriokslive.utils.diff.EventDiffUtilCallback

class EventsAdapter : RecyclerView.Adapter<EventsViewHolder>() {

    private val items = mutableListOf<EventItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventsViewHolder(
        ListItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
    fun setItems(items: List<EventItem>) {
        val diffResult = DiffUtil.calculateDiff(EventDiffUtilCallback(items, this.items))
        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }
}

class EventsViewHolder(private val binding: ListItemEventBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EventItem) {
        with(binding) {
            tvName.text = item.name
            tvType.text = item.type
            tvGrade.text = item.grade
            tvMaxGrade.text = item.maxGrade
            tvWeek.text = item.week
            pbScore.progress = item.progress
            pbScore.setIndicatorColor(ContextCompat.getColor(binding.root.context, item.color))
        }
    }
}
