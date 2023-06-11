package ru.eva.oriokslive.ui.fragment.groups

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.FragmentGroupsBinding
import ru.eva.oriokslive.ui.activity.group.GroupActivity
import ru.eva.oriokslive.ui.activity.schedule.SchedulerActivity
import ru.eva.oriokslive.ui.adapter.GroupAdapter
import ru.eva.oriokslive.ui.base.BaseFragment
import ru.eva.oriokslive.utils.showToast


@AndroidEntryPoint
class GroupsFragment : BaseFragment<FragmentGroupsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGroupsBinding =
        FragmentGroupsBinding::inflate
    private val viewModel: GroupsViewModel by viewModels()

    private val adapter: GroupAdapter by lazy {
        GroupAdapter(
            { startSchedulerActivity(it) },
            { addPinnedShortcuts(it) },
            { group, position -> deleteItem(group, position) }
        )
    }
    private val launcher = registerForActivityResult(StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            it.data?.getStringExtra(EXTRA_GROUP)?.let { group -> viewModel.addSchedule(group) }
        }
    }

    override fun setupUI() {
        binding.btnAdd.setOnClickListener {
            launcher.launch(Intent(requireContext(), GroupActivity::class.java))
        }
        binding.rvGroups.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGroups.adapter = adapter

        viewModel.getGroups()
        viewModel.groups.observe(viewLifecycleOwner) { adapter.addItems(it) }
        viewModel.onError.observe(viewLifecycleOwner) { requireContext().showToast(it) }
    }

    private fun startSchedulerActivity(group: String) {
        val intent = Intent(requireContext(), SchedulerActivity::class.java)
        intent.putExtra(EXTRA_GROUP, group)
        startActivity(intent)
    }

    private fun addPinnedShortcuts(group: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setClass(requireContext(), SchedulerActivity::class.java)
        intent.putExtra(EXTRA_GROUP, group)
        val shortcut = ShortcutInfoCompat.Builder(requireContext(), group)
            .setShortLabel(group.split("\\s")[0])
            .setLongLabel(group)
            .setIcon(IconCompat.createWithResource(requireContext(), R.drawable.ic_schedule))
            .setIntent(intent)
            .build()
        ShortcutManagerCompat.pushDynamicShortcut(requireContext(), shortcut)
        requireContext().showToast(R.string.shortcut_added)
    }

    private fun deleteItem(group: String, position: Int) {
        viewModel.removeGroup(group)
        adapter.removeItem(position)
    }

    companion object {
        const val EXTRA_GROUP = "GROUP"
    }
}
