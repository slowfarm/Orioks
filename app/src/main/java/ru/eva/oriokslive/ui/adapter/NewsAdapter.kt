package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ListItemNewsBinding
import ru.eva.oriokslive.network.utils.cookie
import ru.eva.oriokslive.ui.entity.NewsItem

class NewsAdapter(private val listener: (String) -> Unit) : RecyclerView.Adapter<NewsViewHolder>() {

    private var newsItems = listOf<NewsItem>()
    private var cookie: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsItems[position], cookie, listener)
    }

    override fun getItemCount(): Int = newsItems.size

    fun addItems(items: Pair<List<NewsItem>, String?>) {
        newsItems = items.first
        cookie = items.second
        notifyDataSetChanged()
    }
}

class NewsViewHolder(private val binding: ListItemNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NewsItem, cookie: String?, listener: (String) -> Unit) {
        binding.tvTitle.text = item.title
        binding.tvDescription.text = item.description
        binding.tvDate.text = item.date
        cookie?.let {
            Picasso.Builder(binding.root.context)
                .cookie(it)
                .load(item.imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(binding.ivPicture)
        } ?: binding.ivPicture.setImageResource(R.drawable.ic_placeholder)
        binding.root.setOnClickListener { listener.invoke(item.link) }
    }

}
