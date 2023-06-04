package ru.eva.oriokslive.ui.fragment.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.FragmentNewsBinding
import ru.eva.oriokslive.ui.activity.news.NewsActivity
import ru.eva.oriokslive.ui.adapter.NewsAdapter
import ru.eva.oriokslive.ui.base.BaseFragment

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
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.no_connection, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val EXTRA_URL = "URL"
    }
}