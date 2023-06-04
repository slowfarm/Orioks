package ru.eva.oriokslive.utils

import ru.eva.oriokslive.R
import ru.eva.oriokslive.network.entity.news.News
import ru.eva.oriokslive.network.entity.news.NewsResponse
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.network.entity.orioks.Security
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.entity.schedule.Data
import ru.eva.oriokslive.ui.entity.DisciplineItem
import ru.eva.oriokslive.ui.entity.EventItem
import ru.eva.oriokslive.ui.entity.Header

fun mapStudent(student: Student): Header {
    val week = getCurrentWeek()
    val progress = (week.toFloat() / 18 * 100).toInt()
    return Header(week.toString(), progress, getCurrentValue(week), student.fullName, student.group)
}

fun mapEvents(events: List<Event>) = events.map {
    val progress = (it.currentGrade / it.maxGrade * 100).toInt()
    EventItem(
        progress = progress,
        name = it.type,
        grade = if (it.currentGrade == -1.0) "-" else it.currentGrade.toString(),
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

fun mapDay(schedule: List<Data>?): List<Data> {
    var result: MutableList<Data>? = schedule?.toMutableList()
    when {
        result == null -> result = mutableListOf()
        result.isNotEmpty() -> setDataToPosition(result, 0)
        else -> result.add(0, Data())
    }
    return result
}

fun mapWeek(schedule: List<Data>?): List<Data> {
    var result: MutableList<Data>? = schedule?.toMutableList()
    if (result == null) result = mutableListOf() else fillDataList(result)
    return result
}

//    public List<Discipline> disciplines(List<Discipline> disciplineList) {
//        for(Discipline discipline : disciplineList) {
//            if (discipline.getCurrentGrade() != -1.0) {
//                discipline.setCurrentGradeValue(String.valueOf(discipline.getCurrentGrade()));
//            } else {
//                discipline.setCurrentGradeValue("-");
//            }
//            discipline.setMaxGradeValue(String.valueOf(discipline.getMaxGrade()));
//
//            double progress = discipline.getCurrentGrade() / discipline.getMaxGrade() * 100;
//            discipline.setProgress(progress >= 0 ? (int)progress : 0);
//
//            if(progress < 50) {
//                discipline.setColor(Color.parseColor("#FF6D00"));
//            } else if(progress < 70) {
//                discipline.setColor(Color.parseColor("#FFD600"));
//            } else if(progress < 85) {
//                discipline.setColor(Color.parseColor("#64DD17"));
//            } else {
//                discipline.setColor(Color.parseColor("#00C853"));
//            }
//        }
//        return disciplineList;
//    }
fun mapTokens(tokens: List<Security>): MutableList<Security> {
    val result = mutableListOf<Security>()
    for (security in tokens) {
        var item = security
        item = if (security.userAgent.split("\\s").size > 1) {
            item.copy(
                application = security.userAgent.split("\\s")[0],
                device = security.userAgent.replace("\\s", " "),
                containDevice = true
            )
        } else {
            item.copy(
                application = security.userAgent,
                containDevice = false
            )
        }
        item = item.copy(
            lastUsedValue = dataParser(security.lastUsed)
        )
        result.add(item)
    }
    return result
}

fun mapNews(response: NewsResponse): List<News> = response.channel.items.map {
    News(
        title = it.title,
        link = it.link,
        description = it.description,
        imageUrl = it.link,
        date = it.date,
    )
}

private fun fillDataList(dataList: MutableList<Data>) {
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

private fun setDataToPosition(dataList: MutableList<Data>, position: Int) {
    val data = Data(dayOfWeek = getDayOfWeek(dataList[position].day ?: 0))
    dataList.add(position, data)
}
