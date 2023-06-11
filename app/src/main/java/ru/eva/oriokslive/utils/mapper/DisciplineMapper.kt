package ru.eva.oriokslive.utils.mapper

import ru.eva.oriokslive.R
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.ui.entity.DisciplineItem

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
