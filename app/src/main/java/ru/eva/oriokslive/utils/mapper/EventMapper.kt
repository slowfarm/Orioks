package ru.eva.oriokslive.utils.mapper

import ru.eva.oriokslive.App
import ru.eva.oriokslive.R
import ru.eva.oriokslive.network.entity.orioks.Event
import ru.eva.oriokslive.ui.entity.EventItem

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
        name = it.name ?: it.type,
        type = it.type,
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
