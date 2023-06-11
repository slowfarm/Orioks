package ru.eva.oriokslive.utils.mapper

import ru.eva.oriokslive.App
import ru.eva.oriokslive.R
import ru.eva.oriokslive.network.entity.schedule.Data
import ru.eva.oriokslive.ui.entity.ScheduleItem
import ru.eva.oriokslive.utils.getDayOfWeek
import ru.eva.oriokslive.utils.scheduleDateParser

fun mapDay(schedule: List<Data>?): List<ScheduleItem> {
    var result: MutableList<ScheduleItem>? = mapSchedule(schedule)
    when {
        result == null -> result = mutableListOf()
        result.isNotEmpty() -> setDataToPosition(result, 0)
        else -> result.add(0, ScheduleItem(dayOfWeek = R.string.no_classes))
    }
    return result
}

fun mapWeek(schedule: List<Data>?): List<ScheduleItem> {
    var result: MutableList<ScheduleItem>? = mapSchedule(schedule)
    when {
        result == null -> result = mutableListOf()
        result.isNotEmpty() -> fillDataList(result)
        else -> result.add(0, ScheduleItem(dayOfWeek = R.string.no_classes))
    }
    return result
}

private fun mapSchedule(items: List<Data>?) = items?.map {
    ScheduleItem(
        day = it.day,
        dayNumber = it.dayNumber,
        name = it.clazz.name,
        room = App.get().getString(R.string.room, it.room.name),
        teacher = App.get().getString(R.string.teacher, it.clazz.teacher),
        time = App.get().getString(
            R.string.date_range,
            scheduleDateParser(it.time.timeFrom),
            scheduleDateParser(it.time.timeTo),
        ),
        dayOfWeek = getDayOfWeek(it.day),
    )
}?.toMutableList()

private fun fillDataList(dataList: MutableList<ScheduleItem>) {
    var i = 1
    while (i < dataList.size) {
        if (dataList[i].day != dataList[i - 1].day) {
            setDataToPosition(dataList, i)
            i++
        }
        i++
    }
    setDataToPosition(dataList, 0)
}

private fun setDataToPosition(items: MutableList<ScheduleItem>, position: Int) {
    val data = ScheduleItem(dayOfWeek = getDayOfWeek(items[position].day ?: R.string.no_classes))
    items.add(position, data)
}
