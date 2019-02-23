package ru.eva.oriokslive.helpers;

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
        String input = cal.get(Calendar.YEAR)+"0901";
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
        if(week > 18) return 18;
        if(week < 0)
            return getCurrentWeek2Sem();
        return week;
    }

    public int getCurrentWeek2Sem() {
        String format = "yyyyMMdd";

        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        String input = cal.get(Calendar.YEAR)+"0211";
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
        if(week > 18) return 18;
        return week;
    }

    public int calculateCurrentDay() {
        int currentWeek = getCurrentWeek();
        return (currentWeek-1)%4;
    }

    public int getDayOfWeek() {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public int getNextDayOfWeek() {
        if(getDayOfWeek() == 7)
            return 1;
        else
            return getDayOfWeek()+1;
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
        return  value;
    }
}
