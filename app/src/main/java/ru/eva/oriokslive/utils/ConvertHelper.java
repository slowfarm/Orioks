package ru.eva.oriokslive.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConvertHelper {

    private static ConvertHelper instance;

    public static ConvertHelper getInstance() {
        if (instance == null)
            instance = new ConvertHelper();
        return instance;
    }

    public int getCurrentWeek() {
        String format = "yyyyMMdd";

        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        String input = cal.get(Calendar.YEAR) + "0901";
        Date date = null;
        try {
            date = df.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        int startWeek = cal.get(Calendar.WEEK_OF_YEAR);
        cal.setTime(new Date());
        int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
        int week = currentWeek - startWeek + 1;
        if (week > 18) return 18;
        if (week < 0)
            return getCurrentWeek2Sem();
        return week;
    }

    private int getCurrentWeek2Sem() {
        String format = "yyyyMMdd";

        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        String input = cal.get(Calendar.YEAR) + "0211";
        Date date = null;
        try {
            date = df.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        int startWeek = cal.get(Calendar.WEEK_OF_YEAR);
        cal.setTime(new Date());
        int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
        int week = currentWeek - startWeek + 1;
        if (week > 18) return 18;
        return week;
    }

    public int calculateCurrentDay() {
        int currentWeek = getCurrentWeek();
        return (currentWeek - 1) % 4;
    }

    public int getDayOfWeek() {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public int getNextDayOfWeek() {
        if (getDayOfWeek() == 7)
            return 1;
        else
            return getDayOfWeek() + 1;
    }

    public String getCurrentValue(int week) {
        String value;
        switch (week % 4) {
            case 0:
                value = "2 Знаменатель";
                break;
            case 1:
                value = "1 Числитель";
                break;
            case 2:
                value = "1 Знаменатель";
                break;
            case 3:
                value = "2 Числитель";
                break;
            default:
                value = "1 Числитель";
                break;
        }
        return value;
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

    private String newsDateParser(String inputDate) {
        String oldPattern = "EEE, dd MMM yyyy HH:mm:ss Z";
        String newPattern = "d.MM.yyyy HH:mm";

        SimpleDateFormat sdf = new SimpleDateFormat(oldPattern, Locale.ENGLISH);
        Date date = new Date();
        try {
            date = sdf.parse(inputDate);
            sdf.applyPattern(newPattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }



    private String getDayOfWeek(int day) {
        switch (day) {
            case 1:
                return "Понедельник";
            case 2:
                return "Вторник";
            case 3:
                return "Среда";
            case 4:
                return "Четверг";
            case 5:
                return "Пятница";
            case 6:
                return "Суббота";
            case 7:
                return "Воскресенье";
            default:
                return "Понедельник";
        }
    }
}
