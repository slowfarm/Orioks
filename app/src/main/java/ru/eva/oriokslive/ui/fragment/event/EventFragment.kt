package ru.eva.oriokslive.ui.fragment.event

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.FragmentEventBinding
import ru.eva.oriokslive.network.exceptions.NetworkException
import ru.eva.oriokslive.ui.activity.events.EventsActivity
import ru.eva.oriokslive.ui.activity.events.EventsActivity.Companion.EXTRA_ID
import ru.eva.oriokslive.ui.activity.registration.RegistrationActivity
import ru.eva.oriokslive.ui.adapter.DisciplineAdapter
import ru.eva.oriokslive.ui.base.BaseFragment
import ru.eva.oriokslive.utils.onRFACItemLabelClick
import ru.eva.oriokslive.utils.showToast

@AndroidEntryPoint
class EventFragment : BaseFragment<FragmentEventBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEventBinding =
        FragmentEventBinding::inflate
    private val viewModel: EventViewModel by viewModels()

    private var fabHelper: RapidFloatingActionHelper? = null

    private val adapter: DisciplineAdapter by lazy {
        DisciplineAdapter {
            val intent = Intent(requireContext(), EventsActivity::class.java).putExtra(EXTRA_ID, it)
            startActivity(intent)
        }
    }

    override fun setupUI() {
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.getDisciplineList() }
        binding.rvGroups.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGroups.adapter = adapter

        viewModel.getDisciplineList()
        viewModel.getSemester()

        viewModel.disciplines.observe(viewLifecycleOwner) {
            adapter.setItems(it)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.semesterItems.observe(viewLifecycleOwner) { initFab(it) }

        viewModel.semesterChanged.observe(viewLifecycleOwner) {
            requireContext().showToast(getString(R.string.semester_changed, it))
            startActivity(Intent(requireContext(), RegistrationActivity::class.java))
            requireActivity().finishAffinity()
        }

        viewModel.onError.observe(viewLifecycleOwner) {
            if (it is NetworkException) viewModel.getLocalDisciplines()
            requireContext().showToast(it)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initFab(items: List<RFACLabelItem<Int>>) {
        val rfaContent = RapidFloatingActionContentLabelList(context)
        rfaContent.onRFACItemLabelClick {
            fabHelper?.toggleContent()
            viewModel.changeSemester(it)
        }
        rfaContent.items = items
        fabHelper =
            RapidFloatingActionHelper(context, binding.fabLayout, binding.fab, rfaContent).build()
    }
}
