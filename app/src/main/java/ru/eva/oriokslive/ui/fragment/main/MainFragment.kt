package ru.eva.oriokslive.ui.fragment.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.FragmentMainBinding
import ru.eva.oriokslive.ui.adapter.DisciplineAdapter
import ru.eva.oriokslive.ui.base.BaseFragment

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding =
        FragmentMainBinding::inflate
    private val viewModel: MainViewModel by viewModels()

    private val adapter: DisciplineAdapter by lazy { DisciplineAdapter() }

    override fun setupUI() {
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.getDisciplineList() }
        binding.rvGroups.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGroups.adapter = adapter

        viewModel.getDisciplineList()

        viewModel.disciplines.observe(viewLifecycleOwner) {
            adapter.addItems(it)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.onError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.no_connection, Toast.LENGTH_LONG).show()
        }
    }
}