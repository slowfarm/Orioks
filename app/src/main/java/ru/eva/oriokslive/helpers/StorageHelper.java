package ru.eva.oriokslive.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import ru.eva.oriokslive.models.orioks.Disciplines;
import ru.eva.oriokslive.models.orioks.Events;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.schedule.Clazz;
import ru.eva.oriokslive.models.schedule.Data;
import ru.eva.oriokslive.models.schedule.Schedulers;

public class StorageHelper {

    private static volatile StorageHelper instance;
    private final String table = "data";

    public static StorageHelper getInstance() {
        if (instance == null)
            instance = new StorageHelper();
        return instance;
    }

    public void setAccessToken(Context context, String accessToken) {
        SharedPreferences sharedPref = context.getSharedPreferences(table, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("accessToken", accessToken);
        editor.apply();
    }

    public String getAccessToken(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(table, Context.MODE_PRIVATE);
        return sharedPref.getString("accessToken", null);
    }

    public void setStudent(Student student) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(Student.class);
            realm.insert(student);
        });
    }

    public Student getStudent() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Student.class).findFirst();
    }

    public void setDisciplines(List<Disciplines> disciplines) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(Disciplines.class);
            realm.insert(disciplines);
        });
    }

    public List<Disciplines> getDisciplines() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Disciplines.class).findAll();
    }

    public Disciplines getDiscipline(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Disciplines.class).equalTo("id", id).findFirst();
    }

    public List<Events> getEventsList(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Events.class).equalTo("id", id).findAll();
    }

    public void setEvents(List<Events> eventsList) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(Events.class);
            realm.insert(eventsList);
        });
    }

    public void clearTables(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(table, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("accessToken", null);
        editor.putString("loginAndPassword", null);
        editor.apply();
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> realm1.deleteAll());
    }

    public void setLoginAndPassword(Context context, String loginAndPassword) {
        SharedPreferences sharedPref = context.getSharedPreferences(table, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("loginAndPassword", loginAndPassword);
        editor.apply();
    }

    public String getLoginAndPassword(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(table, Context.MODE_PRIVATE);
        return sharedPref.getString("loginAndPassword", null);
    }

    public void setSchedulers(Schedulers schedulers) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(Schedulers.class);
            realm.delete(Data.class);
            realm.insert(schedulers);
        });
    }

    public List<Data> getSchedulersDataCurrentWeek(int dayNumber) {
        Realm realm = Realm.getDefaultInstance();
        List<Data> dataList = realm.copyFromRealm(realm.where(Data.class).equalTo("dayNumber", dayNumber).findAll());
        if(dataList == null)
            dataList = new ArrayList<>();
        Log.d("tagddayNumber", dayNumber+"  "+dataList.size()+"");
        return dataList;
    }

    public List<Data> getSchedulersDataCurrentDay(int week, int value) {
        Realm realm = Realm.getDefaultInstance();
        List<Data> dataList = realm.copyFromRealm(
                realm.where(Data.class)
                        .equalTo("dayNumber", week)
                        .equalTo("day",value).findAll());
        if(dataList == null)
            dataList = new ArrayList<>();
        Log.d("tagddayNumber", value+"  "+dataList.size()+"");
        return dataList;
    }

    public void setCurrentWeek(Context context, int currentWeek) {
        SharedPreferences sharedPref = context.getSharedPreferences(table, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("currentWeek", currentWeek);
        editor.apply();
    }

    public int getCurrentWeek(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(table, Context.MODE_PRIVATE);
        return sharedPref.getInt("currentWeek", 0);
    }
}
