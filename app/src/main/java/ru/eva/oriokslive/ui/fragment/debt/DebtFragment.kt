package ru.eva.oriokslive.ui.fragment.debt

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.databinding.FragmentDebtBinding
import ru.eva.oriokslive.network.exceptions.NetworkException
import ru.eva.oriokslive.ui.activity.resit.ResitActivity
import ru.eva.oriokslive.ui.activity.resit.ResitActivity.Companion.EXTRA_ID
import ru.eva.oriokslive.ui.adapter.DisciplineAdapter
import ru.eva.oriokslive.ui.base.BaseFragment
import ru.eva.oriokslive.utils.showToast

@AndroidEntryPoint
class DebtFragment : BaseFragment<FragmentDebtBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDebtBinding =
        FragmentDebtBinding::inflate

    private val viewModel: DebtViewModel by viewModels()

    private val adapter: DisciplineAdapter by lazy {
        DisciplineAdapter {
            val intent = Intent(requireContext(), ResitActivity::class.java).putExtra(EXTRA_ID, it)
            startActivity(intent)
        }
    }

    override fun setupUI() {
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.getDebtList() }
        binding.rvDebt.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDebt.adapter = adapter

        viewModel.getDebtList()

        viewModel.debts.observe(viewLifecycleOwner) {
            adapter.addItems(it)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.onError.observe(viewLifecycleOwner) {
            if (it is NetworkException) viewModel.getLocalDebts()
            requireContext().showToast(it)
        }
    }
}