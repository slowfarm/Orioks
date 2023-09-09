package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.eva.oriokslive.databinding.ListItemResitBinding
import ru.eva.oriokslive.ui.entity.ResitItem
import ru.eva.oriokslive.utils.diff.ResitDiffUtilCallback

class ResitsAdapter : RecyclerView.Adapter<ResitsViewHolder>() {

    private val items = mutableListOf<ResitItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ResitsViewHolder(
        ListItemResitBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )

    override fun onBindViewHolder(holder: ResitsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
    fun setItems(items: List<ResitItem>) {
        val diffResult = DiffUtil.calculateDiff(ResitDiffUtilCallback(items, this.items))
        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }
}

class ResitsViewHolder(private val binding: ListItemResitBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ResitItem) {
        with(binding) {
            tvName.text = item.resitNumber
            tvDate.text = item.datetime
            tvRoom.text = item.room
        }
    }
}
