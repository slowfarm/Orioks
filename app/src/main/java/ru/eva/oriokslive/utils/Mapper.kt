package ru.eva.oriokslive.utils

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
        week = it.week,
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
        else -> result.add(0, ScheduleItem())
    }
    return result
}

fun mapWeek(schedule: List<Data>?): List<ScheduleItem> {
    var result: MutableList<ScheduleItem>? = mapSchedule(schedule)
    when {
        result == null -> result = mutableListOf()
        result.isNotEmpty() -> fillDataList(result)
        else -> result.add(0, ScheduleItem())
    }
    return result
}

fun mapTokens(tokens: List<Security>): MutableList<SecurityItem> {
    val result = mutableListOf<SecurityItem>()
    for (security in tokens) {
        when {
            security.userAgent.split("\\s").size > 1 -> {
                result.add(
                    SecurityItem(
                        security.token,
                        security.userAgent.split("\\s")[0],
                        security.userAgent.replace("\\s", " "),
                        true,
                        dateParser(security.lastUsed)
                    )
                )
            }
            else -> {
                result.add(
                    SecurityItem(
                        security.token,
                        security.userAgent,
                        security.userAgent,
                        false,
                        dateParser(security.lastUsed)
                    )
                )
            }
        }
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
        time = it.time.copy(
            timeFrom = scheduleDateParser(it.time.timeFrom),
            timeTo = scheduleDateParser(it.time.timeTo)
        ),
        clazz = it.clazz,
        group = it.group,
        room = it.room,
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
    val data = ScheduleItem(dayOfWeek = getDayOfWeek(items[position].day ?: 0))
    items.add(position, data)
}
