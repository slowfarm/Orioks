package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import ru.eva.oriokslive.databinding.ListItemSecurityBinding
import ru.eva.oriokslive.ui.entity.SecurityItem


class SecurityFragmentAdapter(
    private val listener: (String, Int) -> Unit,
) : RecyclerView.Adapter<SecurityViewHolder>() {

    private var tokens = mutableListOf<SecurityItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SecurityViewHolder(
        ListItemSecurityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = tokens.size

    override fun onBindViewHolder(holder: SecurityViewHolder, position: Int) {
        holder.bind(tokens[position], listener)
    }

    fun addItems(items: List<SecurityItem>) {
        tokens = items.toMutableList()
        notifyDataSetChanged()
    }
}

class SecurityViewHolder(private val binding: ListItemSecurityBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val binderHelper = ViewBinderHelper()

    fun bind(item: SecurityItem, listener: (String, Int) -> Unit) {
        binderHelper.bind(binding.swipeLayout, item.token)
        binding.tvApplication.text = item.application
        binding.tvDevice.text = item.device
        binding.tvDevice.visibility = if (item.containDevice) View.VISIBLE else View.GONE
        binding.tvDate.text = item.lastUsed
        binding.flDelete.setOnClickListener {
            listener.invoke(item.token, adapterPosition)
            binding.swipeLayout.close(true)
        }
    }
}
