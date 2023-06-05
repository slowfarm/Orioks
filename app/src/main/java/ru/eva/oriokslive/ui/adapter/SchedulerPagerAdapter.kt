package ru.eva.oriokslive.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.eva.oriokslive.R
import ru.eva.oriokslive.ui.fragment.schedule.ScheduleFragment


class SchedulerPagerAdapter(activity: FragmentActivity, private val group: String) :
    FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment =
        ScheduleFragment.newInstance(group, position)

    override fun getItemCount() = titles.size

    companion object {
        val titles = listOf(
            R.string.today,
            R.string.tomorrow,
            R.string.first_numerator,
            R.string.first_delimiter,
            R.string.second_numerator,
            R.string.second_delimiter,
        )
    }
}