package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.eva.oriokslive.databinding.ListItemGroupBinding


class GroupAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<GroupViewHolder>() {

    private var groups: List<String> = listOf()

    fun setItems(items: List<String>) {
        groups = items
        notifyItemRangeChanged(0, groups.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GroupViewHolder(
        ListItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(groups[position], listener)
    }

    override fun getItemCount(): Int = groups.size
}

class GroupViewHolder(private val binding: ListItemGroupBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(group: String, listener: (String) -> Unit) {
        binding.tvGroup.text = group
        binding.root.setOnClickListener { listener.invoke(group) }
    }
}