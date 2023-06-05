package ru.eva.oriokslive.ui.fragment.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.databinding.FragmentNewsBinding
import ru.eva.oriokslive.ui.activity.news.NewsActivity
import ru.eva.oriokslive.ui.adapter.NewsAdapter
import ru.eva.oriokslive.ui.base.BaseFragment
import ru.eva.oriokslive.utils.showToast

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNewsBinding =
        FragmentNewsBinding::inflate
    private val viewModel: NewsViewModel by viewModels()

    private val adapter: NewsAdapter by lazy {
        NewsAdapter {
            val intent = Intent(requireContext(), NewsActivity::class.java)
            intent.putExtra(EXTRA_URL, it)
            startActivity(intent)
        }
    }

    override fun setupUI() {
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.getNews() }

        viewModel.getNews()
        viewModel.news.observe(viewLifecycleOwner) {
            binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
            binding.rvNews.adapter = adapter
            adapter.addItems(it)
            binding.swipeRefreshLayout.isRefreshing = false
        }
        viewModel.onError.observe(viewLifecycleOwner) { requireContext().showToast(it) }
    }

    companion object {
        const val EXTRA_URL = "URL"
    }
}