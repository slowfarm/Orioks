package ru.eva.oriokslive.utils

import ru.eva.oriokslive.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit.DAYS
import java.util.concurrent.TimeUnit.HOURS
import java.util.concurrent.TimeUnit.MILLISECONDS

fun getCurrentValue(week: Int): Int = when (week % 4) {
    0 -> R.string.second_delimiter
    1 -> R.string.first_numerator
    2 -> R.string.first_delimiter
    3 -> R.string.second_numerator
    else -> R.string.first_numerator
}

fun dayNumber(): Int {
    val currentWeek: Int = getCurrentWeek()
    return (currentWeek - 1) % 4
}

fun getDayOfWeek(): Int = Calendar.getInstance(Locale.getDefault())[Calendar.DAY_OF_WEEK] - 1

fun getNextDayOfWeek(): Int = if (getDayOfWeek() == 7) 1 else getDayOfWeek() + 1

fun getDayOfWeek(day: Int): String {
    return when (day) {
        1 -> "Понедельник"
        2 -> "Вторник"
        3 -> "Среда"
        4 -> "Четверг"
        5 -> "Пятница"
        6 -> "Суббота"
        7 -> "Воскресенье"
        else -> "Понедельник"
    }
}

fun getCurrentWeek(): Int {
    val df = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val cal: Calendar = Calendar.getInstance()
    cal.time = df.parse("${cal.get(Calendar.YEAR)}0901") ?: Date()
    val startWeek: Int = cal.get(Calendar.WEEK_OF_YEAR)
    cal.time = Date()
    val currentWeek: Int = cal.get(Calendar.WEEK_OF_YEAR)
    val week = currentWeek - startWeek + 1
    if (week > 18) return 18
    return if (week < 0) getCurrentWeek2Sem() else week
}

fun dateParser(inputDate: String): String {
    val oldPattern = "yyyy-MM-dd'T'HH:mm"
    val newPattern = "HH:mm dd.MM.yyy"
    val sdf = SimpleDateFormat(oldPattern, Locale.getDefault())
    val date: Date = sdf.parse(inputDate) ?: Date()
    sdf.applyPattern(newPattern)
    val dateNow = Date()
    val hour = 1000 * 60 * 60
    val day = hour * 24
    val week = day * 7
    val diff = dateNow.time - date.time - 3 * hour
    if (diff < hour) return "Недавно"
    if (diff < day) {
        return if (HOURS.convert(diff, MILLISECONDS) == 1L) {
            HOURS.convert(diff, MILLISECONDS).toString() + " час назад"
        } else {
            HOURS.convert(diff, MILLISECONDS).toString() + " часа(ов) назад"
        }
    }
    return if (diff < week) {
        if (DAYS.convert(diff, MILLISECONDS) == 1L) {
            DAYS.convert(diff, MILLISECONDS).toString() + " день назад"
        } else {
            DAYS.convert(diff, MILLISECONDS).toString() + " дня(ей) назад"
        }
    } else {
        sdf.format(date)
    }
}

fun scheduleDateParser(inputDate: String): String {
    val oldPattern = "yyyy-MM-dd'T'HH:mm"
    val newPattern = "HH:mm"
    val sdf = SimpleDateFormat(oldPattern, Locale.getDefault())
    val date: Date = sdf.parse(inputDate) ?: Date()
    sdf.applyPattern(newPattern)
    return sdf.format(date)
}

fun newsDateParser(inputDate: String): String {
    val oldPattern = "EEE, dd MMM yyyy HH:mm:ss Z"
    val newPattern = "d.MM.yyyy HH:mm"
    val sdf = SimpleDateFormat(oldPattern, Locale.ENGLISH)
    val date: Date = sdf.parse(inputDate) ?: Date()
    sdf.applyPattern(newPattern)
    return sdf.format(date)
}

private fun getCurrentWeek2Sem(): Int {
    val df = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val cal: Calendar = Calendar.getInstance()
    cal.time = df.parse("${cal.get(Calendar.YEAR)}0211") ?: Date()
    val startWeek = cal[Calendar.WEEK_OF_YEAR]
    cal.time = Date()
    val currentWeek = cal[Calendar.WEEK_OF_YEAR]
    val week = currentWeek - startWeek + 1
    return if (week > 18) 18 else week
}