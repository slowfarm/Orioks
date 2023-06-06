package ru.eva.oriokslive.utils

import ru.eva.oriokslive.App
import ru.eva.oriokslive.R
import ru.eva.oriokslive.network.entity.news.NewsResponse
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Security
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.entity.schedule.Data
import ru.eva.oriokslive.ui.entity.DisciplineItem
import ru.eva.oriokslive.ui.entity.EventItem
import ru.eva.oriokslive.ui.entity.Header
import ru.eva.oriokslive.ui.entity.NewsItem
import ru.eva.oriokslive.ui.entity.ScheduleItem
import ru.eva.oriokslive.ui.entity.SecurityItem

fun mapStudent(student: Student): Header {
    val week = getCurrentWeek()
    val progress = (week.toFloat() / 18 * 100).toInt()
    return Header(week.toString(), progress, getCurrentValue(week), student.fullName, student.group)
}

fun mapEvents(events: List<Event>) = events.map {
    val progress: Int
    val grade: String
    when (it.currentGrade) {
        null -> {
            progress = 0
            grade = "н"
        }
        -1.0 -> {
            progress = 0
            grade = "-"
        }
        else -> {
            progress = (it.currentGrade / it.maxGrade * 100).toInt()
            grade = it.currentGrade.toString()
        }
    }
    EventItem(
        progress = progress,
        name = it.type,
        grade = grade,
        maxGrade = it.maxGrade.toString(),
        color = when {
            progress < 50 -> R.color.progress50
            progress < 70 -> R.color.progress70
            progress < 85 -> R.color.progress85
            else -> R.color.progress100
        },
        week = App.get().getString(R.string.week, it.week),
    )
}

fun mapDisciplines(disciplines: List<Discipline>) = disciplines.map {
    val progress = (it.currentGrade / it.maxGrade * 100).toInt()
    DisciplineItem(
        id = it.id,
        progress = progress,
        name = it.name,
        grade = if (it.currentGrade == -1.0) "-" else it.currentGrade.toString(),
        maxGrade = it.maxGrade.toString(),
        color = when {
            progress < 50 -> R.color.progress50
            progress < 70 -> R.color.progress70
            progress < 85 -> R.color.progress85
            else -> R.color.progress100
        },
    )
}

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

fun mapTokens(tokens: List<Security>): MutableList<SecurityItem> {
    val result = mutableListOf<SecurityItem>()
    for (security in tokens) {
        val containDevice = security.userAgent.split(" ").size > 1
        val application =
            if (containDevice) security.userAgent.substringBefore(" ") else security.userAgent
        val device =
            if (containDevice) security.userAgent.substringAfter(" ") else security.userAgent
        result.add(
            SecurityItem(
                security.token,
                App.get().getString(R.string.app, application),
                App.get().getString(R.string.device, device),
                containDevice,
                App.get().getString(R.string.last_activity, dateParser(security.lastUsed))
            )
        )
    }
    return result
}

fun mapNews(response: NewsResponse): List<NewsItem> = response.channel.items.map {
    NewsItem(
        title = it.title,
        link = it.link,
        description = it.description,
        imageUrl = it.enclosure.url,
        date = newsDateParser(it.date),
    )
}

private fun mapSchedule(items: List<Data>?) = items?.map {
    ScheduleItem(
        day = it.day,
        dayNumber = it.dayNumber,
        name = it.clazz.name,
        room = App.get().getString(R.string.room_name, it.room.name),
        teacher = App.get().getString(R.string.teacher_name, it.clazz.teacher),
        time = App.get().getString(
            R.string.date_range,
            scheduleDateParser(it.time.timeFrom),
            scheduleDateParser(it.time.timeTo)
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
