package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.eva.oriokslive.databinding.ListItemDisciplineBinding
import ru.eva.oriokslive.ui.entity.DisciplineItem

class DisciplineAdapter : RecyclerView.Adapter<DisciplineViewHolder?>() {

    private var disciplines: List<DisciplineItem> = listOf()

    fun addItems(items: List<DisciplineItem>) {
        disciplines = items
        notifyItemRangeChanged(0, disciplines.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DisciplineViewHolder(
        ListItemDisciplineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DisciplineViewHolder, position: Int) {
        holder.bind(disciplines[position])
    }

    override fun getItemCount(): Int = disciplines.size
}

class DisciplineViewHolder(private val binding: ListItemDisciplineBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(eventItem: DisciplineItem) {
        with(binding) {
            tvName.text = eventItem.name
            tvGrade.text = eventItem.grade
            tvMaxGrade.text = eventItem.maxGrade
            pbScore.progress = eventItem.progress
            pbScore.setIndicatorColor(eventItem.color)
        }
    }
}