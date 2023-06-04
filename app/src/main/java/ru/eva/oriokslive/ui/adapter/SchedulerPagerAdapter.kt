package ru.eva.oriokslive.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.eva.oriokslive.ui.fragment.schedule.ScheduleFragment


class SchedulerPagerAdapter(activity: FragmentActivity, private val group: String) :
    FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment =
        ScheduleFragment.newInstance(group, titles[position], position)

    override fun getItemCount() = titles.size

    companion object {
        val titles = listOf(
            "Сегодня",
            "Завтра",
            "1 Числитель",
            "1 Знаменатель",
            "2 Числитель",
            "2 Знаменатель"
        )
    }
}