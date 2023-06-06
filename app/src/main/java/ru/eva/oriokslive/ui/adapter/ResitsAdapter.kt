package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.eva.oriokslive.databinding.ListItemResitBinding
import ru.eva.oriokslive.ui.entity.ResitItem

class ResitsAdapter(private val resits: List<ResitItem>) :
    RecyclerView.Adapter<ResitsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ResitsViewHolder(
        ListItemResitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ResitsViewHolder, position: Int) {
        holder.bind(resits[position])
    }

    override fun getItemCount() = resits.size
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