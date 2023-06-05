package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.eva.oriokslive.databinding.ListItemDisciplineBinding
import ru.eva.oriokslive.ui.entity.DisciplineItem

class DisciplineAdapter(private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<DisciplineViewHolder?>() {

    private var disciplines: List<DisciplineItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DisciplineViewHolder(
        ListItemDisciplineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DisciplineViewHolder, position: Int) {
        holder.bind(disciplines[position], listener)
    }

    override fun getItemCount(): Int = disciplines.size

    fun addItems(items: List<DisciplineItem>) {
        disciplines = items
        notifyDataSetChanged()
    }
}

class DisciplineViewHolder(private val binding: ListItemDisciplineBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DisciplineItem, listener: (Int) -> Unit) {
        with(binding) {
            tvName.text = item.name
            tvGrade.text = item.grade
            tvMaxGrade.text = item.maxGrade
            pbScore.progress = item.progress
            pbScore.setIndicatorColor(ContextCompat.getColor(binding.root.context, item.color))
            binding.root.setOnClickListener { listener.invoke(item.id) }
        }
    }
}