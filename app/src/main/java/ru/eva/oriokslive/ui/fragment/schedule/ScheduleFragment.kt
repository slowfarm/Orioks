package ru.eva.oriokslive.ui.fragment.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.databinding.FragmentScheduleBinding
import ru.eva.oriokslive.ui.adapter.ScheduleAdapter
import ru.eva.oriokslive.ui.base.BaseFragment

@AndroidEntryPoint
class ScheduleFragment : BaseFragment<FragmentScheduleBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentScheduleBinding =
        FragmentScheduleBinding::inflate

    private val viewModel: ScheduleViewModel by viewModels()

    override fun setupUI() {
        binding.rvSchedule.layoutManager = LinearLayoutManager(requireContext())

        arguments?.apply {
            getString(GROUP)?.let { viewModel.getSchedule(it, getInt(POSITION)) }
        }

        viewModel.schedule.observe(viewLifecycleOwner) {
            binding.rvSchedule.adapter = ScheduleAdapter(it)
        }
    }

    companion object {
        private const val GROUP = "GROUP"
        private const val TITLE = "TITLE"
        private const val POSITION = "POSITION"

        @JvmStatic
        fun newInstance(group: String, title: String, position: Int) = ScheduleFragment().apply {
            arguments = Bundle().apply {
                putString(GROUP, group)
                putString(TITLE, title)
                putInt(POSITION, position)
            }
        }
    }
}