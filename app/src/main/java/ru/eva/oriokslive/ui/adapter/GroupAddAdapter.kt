package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.eva.oriokslive.databinding.ListItemGroupAddBinding


class GroupAddAdapter(
    private val listener: (String) -> Unit,
) : RecyclerView.Adapter<GroupAddViewHolder>() {

    private var groups: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GroupAddViewHolder(
        ListItemGroupAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GroupAddViewHolder, position: Int) {
        holder.bind(groups[position], listener)
    }

    override fun getItemCount(): Int = groups.size

    fun addItems(items: List<String>) {
        groups = items.toMutableList()
        notifyDataSetChanged()
    }
}

class GroupAddViewHolder(private val binding: ListItemGroupAddBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(item: String, listener: (String) -> Unit) {
        binding.tvGroup.text = item
        binding.root.setOnClickListener { listener.invoke(item) }
    }
}
