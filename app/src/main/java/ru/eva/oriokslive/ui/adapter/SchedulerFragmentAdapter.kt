package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import ru.eva.oriokslive.databinding.ListItemGroupBinding


class SchedulerFragmentAdapter(
    private val itemClickListener: (String) -> Unit,
    private val addListener: (String) -> Unit,
    private val deleteListener: (String, Int) -> Unit,
) : RecyclerView.Adapter<ScheduleFragmentViewHolder>() {

    private var groups: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ScheduleFragmentViewHolder(
        ListItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ScheduleFragmentViewHolder, position: Int) {
        holder.bind(groups[position], itemClickListener, addListener, deleteListener)
    }

    override fun getItemCount(): Int = groups.size

    fun addItems(items: List<String>) {
        groups = items.toMutableList()
        notifyItemRangeChanged(0, groups.size)
    }

    fun removeItem(position: Int) {
        groups.removeAt(position)
        notifyItemRemoved(position)
    }
}

class ScheduleFragmentViewHolder(private val binding: ListItemGroupBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val binderHelper = ViewBinderHelper()

    fun bind(
        group: String,
        itemClickListener: (String) -> Unit,
        addListener: (String) -> Unit,
        deleteListener: (String, Int) -> Unit,
    ) {
        binderHelper.bind(binding.swipeRevealLayout, group)
        binding.tvGroup.text = group
        binding.frontLayout.setOnClickListener { itemClickListener.invoke(group) }
        binding.add.setOnClickListener {
            addListener.invoke(group)
            binding.swipeRevealLayout.close(true)
        }
        binding.delete.setOnClickListener {
            deleteListener.invoke(group, adapterPosition)
            binding.swipeRevealLayout.close(true)
        }
    }
}
