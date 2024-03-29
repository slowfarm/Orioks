package ru.eva.oriokslive.ui.fragment.security

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.FragmentSecurityBinding
import ru.eva.oriokslive.ui.activity.registration.RegistrationActivity
import ru.eva.oriokslive.ui.adapter.SecurityFragmentAdapter
import ru.eva.oriokslive.ui.base.BaseFragment
import ru.eva.oriokslive.utils.showToast

@AndroidEntryPoint
class SecurityFragment : BaseFragment<FragmentSecurityBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSecurityBinding =
        FragmentSecurityBinding::inflate
    private val viewModel: SecurityViewModel by viewModels()

    private val adapter: SecurityFragmentAdapter by lazy {
        SecurityFragmentAdapter { token, position -> viewModel.deleteToken(token, position) }
    }

    override fun setupUI() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getActiveTokens()
        }
        binding.rvSecurity.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSecurity.adapter = adapter

        viewModel.getActiveTokens()
        viewModel.tokens.observe(viewLifecycleOwner) {
            adapter.addItems(it)
            binding.swipeRefreshLayout.isRefreshing = false
        }
        viewModel.onError.observe(viewLifecycleOwner) {
            requireContext().showToast(it)
            binding.swipeRefreshLayout.isRefreshing = false
        }
        viewModel.finishActivity.observe(viewLifecycleOwner) {
            requireContext().showToast(R.string.token_deleted)
            requireActivity().finishAffinity()
            startActivity(Intent(requireContext(), RegistrationActivity::class.java))
        }
    }
}
