package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import ru.eva.oriokslive.databinding.ListItemSecurityBinding
import ru.eva.oriokslive.network.entity.orioks.Security


class SecurityFragmentAdapter(
    private val listener: (Security, Int) -> Unit,
) : RecyclerView.Adapter<SecurityViewHolder>() {

    private var tokens = mutableListOf<Security>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SecurityViewHolder(
        ListItemSecurityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = tokens.size

    override fun onBindViewHolder(holder: SecurityViewHolder, position: Int) {
        holder.bind(tokens[position], listener)
    }

    fun addItems(items: List<Security>) {
        tokens = items.toMutableList()
        notifyItemRangeChanged(0, tokens.size)
    }

    fun removeItem(position: Int) {
        tokens.removeAt(position)
        notifyItemRemoved(position)
    }
}

class SecurityViewHolder(private val binding: ListItemSecurityBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val binderHelper = ViewBinderHelper()

    fun bind(item: Security, listener: (Security, Int) -> Unit) {
        binderHelper.bind(binding.swipeLayout, item.token)
        binding.tvApplication.text = item.application
        binding.tvDevice.text = item.device
        binding.llDevice.visibility = if (item.containDevice) View.VISIBLE else View.GONE
        binding.tvDate.text = item.lastUsedValue
        binding.flDelete.setOnClickListener {
            listener.invoke(item, adapterPosition)
            binding.swipeLayout.close(true);
        }
    }
}
