package ru.eva.oriokslive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ListItemNewsBinding
import ru.eva.oriokslive.network.entity.news.News

class NewsAdapter(private val listener: (String) -> Unit) : RecyclerView.Adapter<NewsViewHolder>() {

    private var news = listOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position], listener)
    }

    override fun getItemCount(): Int = news.size

    fun addItems(items: List<News>) {
        news = items
        notifyItemRangeChanged(0, news.size)
    }

}

class NewsViewHolder(private val binding: ListItemNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(news: News, listener: (String) -> Unit) {
        binding.tvTitle.text = news.title
        binding.tvDescription.text = news.description
        binding.tvDate.text = news.date
        Picasso.get()
            .load(news.imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(binding.ivPicture)
        binding.root.setOnClickListener { listener.invoke(news.link) }
    }

}
