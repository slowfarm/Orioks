package ru.eva.oriokslive.ui.activity.schedule

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ActivityScheduleBinding
import ru.eva.oriokslive.ui.adapter.SchedulerPagerAdapter
import ru.eva.oriokslive.ui.adapter.SchedulerPagerAdapter.Companion.titles
import ru.eva.oriokslive.ui.base.BaseActivity
import ru.eva.oriokslive.ui.fragment.groups.GroupsFragment.Companion.EXTRA_GROUP
import ru.eva.oriokslive.utils.showToast

@AndroidEntryPoint
class SchedulerActivity : BaseActivity<ActivityScheduleBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityScheduleBinding =
        ActivityScheduleBinding::inflate

    private val viewModel: ScheduleViewModel by viewModels()

    override fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val group = intent.getStringExtra(EXTRA_GROUP) ?: ""
        val adapter = SchedulerPagerAdapter(this, group)

        viewModel.getSchedule(group)
        viewModel.scheduleExist.observe(this) {
            binding.viewPager.adapter = adapter
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.setText(titles[position])
            }.attach()
        }
        viewModel.onError.observe(this) { showToast(it) }
        viewModel.viewPagerPosition.observe(this) {
            binding.viewPager.currentItem = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_date -> {
            viewModel.setViewPagerToCurrentWeek()
            true
        }
        else -> {
            onBackPressed()
            true
        }
    }
}
