package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import ru.eva.oriokslive.databinding.ListItemGroupBinding


class GroupAdapter(
    private val itemClickListener: (String) -> Unit,
    private val addListener: (String) -> Unit,
    private val deleteListener: (String, Int) -> Unit,
) : RecyclerView.Adapter<GroupFragmentViewHolder>() {

    private var groups: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GroupFragmentViewHolder(
        ListItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GroupFragmentViewHolder, position: Int) {
        holder.bind(groups[position], itemClickListener, addListener, deleteListener)
    }

    override fun getItemCount(): Int = groups.size

    fun addItems(items: List<String>) {
        groups = items.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        groups.removeAt(position)
        notifyItemRemoved(position)
    }
}

class GroupFragmentViewHolder(private val binding: ListItemGroupBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val binderHelper = ViewBinderHelper()

    fun bind(
        item: String,
        itemClickListener: (String) -> Unit,
        addListener: (String) -> Unit,
        deleteListener: (String, Int) -> Unit,
    ) {
        binderHelper.bind(binding.swipeRevealLayout, item)
        binding.tvGroup.text = item
        binding.frontLayout.setOnClickListener { itemClickListener.invoke(item) }
        binding.add.setOnClickListener {
            addListener.invoke(item)
            binding.swipeRevealLayout.close(true)
        }
        binding.delete.setOnClickListener {
            deleteListener.invoke(item, adapterPosition)
            binding.swipeRevealLayout.close(true)
        }
    }
}
