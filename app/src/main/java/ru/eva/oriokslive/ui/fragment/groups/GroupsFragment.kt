package ru.eva.oriokslive.ui.fragment.groups

import android.app.Activity.RESULT_OK
import android.app.PendingIntent.getBroadcast
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.FragmentGroupsBinding
import ru.eva.oriokslive.ui.activity.schedule.SchedulerActivity
import ru.eva.oriokslive.ui.activity.group.GroupActivity
import ru.eva.oriokslive.ui.adapter.SchedulerFragmentAdapter
import ru.eva.oriokslive.ui.base.BaseFragment


@AndroidEntryPoint
class GroupsFragment : BaseFragment<FragmentGroupsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGroupsBinding =
        FragmentGroupsBinding::inflate
    private val viewModel: GroupsViewModel by viewModels()

    private val adapter: SchedulerFragmentAdapter by lazy {
        SchedulerFragmentAdapter(
            { startSchedulerActivity(it) },
            { addPinnedShortcuts(it) },
            { group, position ->
                viewModel.removeGroup(group)
                adapter.removeItem(position)
            }
        )
    }

    override fun setupUI() {
        binding.btnAdd.setOnClickListener {
            startActivityForResult(Intent(requireContext(), GroupActivity::class.java), 1)
        }
        binding.rvGroups.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGroups.adapter = adapter

        viewModel.getGroups()
        viewModel.groups.observe(viewLifecycleOwner) { adapter.addItems(it) }
    }

    private fun addPinnedShortcuts(group: String) {
        val shortcutManager = requireContext().getSystemService(ShortcutManager::class.java)
        if (shortcutManager.isRequestPinShortcutSupported) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setClass(requireContext(), SchedulerActivity::class.java)
                putExtra(EXTRA_GROUP, group)
            }
            val info = ShortcutInfo.Builder(requireContext(), group)
                .setShortLabel(group.split("\\s")[0])
                .setLongLabel(group)
                .setIcon(Icon.createWithResource(requireContext(), R.drawable.ic_schedule))
                .setIntent(intent)
                .build()
            val callbackIntent = shortcutManager.createShortcutResultIntent(info)
            val successCallback = getBroadcast(requireContext(), 0, callbackIntent, 0)
            shortcutManager.requestPinShortcut(info, successCallback.intentSender)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            data?.getStringExtra(EXTRA_GROUP)?.let { viewModel.addSchedule(it) }
        }
    }

    private fun startSchedulerActivity(group: String) {
        val intent =
            Intent(requireContext(), SchedulerActivity::class.java).putExtra(EXTRA_GROUP, group)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_GROUP = "GROUP"
    }
}
