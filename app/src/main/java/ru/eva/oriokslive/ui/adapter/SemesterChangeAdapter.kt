package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.eva.oriokslive.databinding.ListItemSemesterBinding
import ru.eva.oriokslive.network.entity.SemesterList
import ru.eva.oriokslive.ui.adapter.SemesterChangeAdapter.SemesterChangeViewHolder

class SemesterChangeAdapter(
    private val items: List<SemesterList>,
    private val listener: (Int) -> Unit,
) :
    RecyclerView.Adapter<SemesterChangeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SemesterChangeViewHolder(
        ListItemSemesterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SemesterChangeViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class SemesterChangeViewHolder(private val binding: ListItemSemesterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SemesterList, listener: (Int) -> Unit) {
            binding.tvSemester.text = item.name
            binding.root.setOnClickListener { listener.invoke(item.id) }
        }
    }
}