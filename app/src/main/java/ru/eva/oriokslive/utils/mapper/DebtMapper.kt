package ru.eva.oriokslive.utils.mapper

import ru.eva.oriokslive.R
import ru.eva.oriokslive.network.entity.orioks.Debt
import ru.eva.oriokslive.ui.entity.DisciplineItem

fun mapDebts(disciplines: List<Debt>) = disciplines.map {
    val progress = (it.currentScore / it.maxScore * 100).toInt()
    DisciplineItem(
        id = it.id,
        progress = progress,
        name = it.name,
        grade = if (it.currentScore == -1.0) "-" else it.currentScore.toString(),
        maxGrade = it.maxScore.toString(),
        color = when {
            progress < 50 -> R.color.progress50
            progress < 70 -> R.color.progress70
            progress < 85 -> R.color.progress85
            else -> R.color.progress100
        },
    )
}
