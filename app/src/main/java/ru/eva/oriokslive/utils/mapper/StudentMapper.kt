package ru.eva.oriokslive.utils.mapper

import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.ui.entity.Header
import ru.eva.oriokslive.utils.getCurrentValue
import ru.eva.oriokslive.utils.getCurrentWeek

fun mapStudent(student: Student): Header {
    val week = getCurrentWeek()
    val progress = (week.toFloat() / 18 * 100).toInt()
    return Header(week.toString(), progress, getCurrentValue(week), student.fullName, student.group)
}