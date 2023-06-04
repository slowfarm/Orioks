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

fun calculateCurrentDay(): Int {
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
//    public List<Event> events(List<Event> eventList) {
//        for(Event event: eventList) {
//            double progress = 0;
//            if(event.getCurrentGrade() != null) {
//                progress = event.getCurrentGrade() / event.getMaxGrade() * 100;
//                if (event.getCurrentGrade() != -1.0)
//                    event.setCurrentGradeValue(String.valueOf(event.getCurrentGrade()));
//                else
//                    event.setCurrentGradeValue("Н");
//            } else {
//                event.setCurrentGradeValue("-");
//            }
//
//            event.setMaxGradeValue(String.valueOf(event.getMaxGrade()));
//
//            event.setProgress(progress >= 0 ? (int)progress : 0);
//
//            if(progress < 50) {
//                event.setColor(Color.parseColor("#FF6D00"));
//            } else if(progress < 70) {
//                event.setColor(Color.parseColor("#FFD600"));
//            } else if(progress < 85) {
//                event.setColor(Color.parseColor("#64DD17"));
//            } else {
//                event.setColor(Color.parseColor("#00C853"));
//            }
//        }
//        return eventList;
//    }
//
fun dataParser(inputDate: String): String {
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