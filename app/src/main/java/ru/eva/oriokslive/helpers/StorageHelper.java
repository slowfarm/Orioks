package ru.eva.oriokslive.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Disciplines;
import ru.eva.oriokslive.models.orioks.Events;
import ru.eva.oriokslive.models.orioks.Security;
import ru.eva.oriokslive.models.orioks.Student;
import ru.eva.oriokslive.models.schedule.Data;
import ru.eva.oriokslive.models.schedule.Schedulers;

public class StorageHelper {

    private static volatile StorageHelper instance;

    public static StorageHelper getInstance() {
        if (instance == null)
            instance = new StorageHelper();
        return instance;
    }

    public void setAccessToken(AccessToken accessToken) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(AccessToken.class);
            realm.insert(accessToken);
        });
    }

    public String getAccessToken() {
        Realm realm = Realm.getDefaultInstance();
        AccessToken accessToken = realm.where(AccessToken.class).findFirst();
        if(accessToken != null) {
            return accessToken.getToken();
        }
        else {
            return null;
        }
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

    public void clearTables() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.deleteAll());
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
        RealmResults<Data> results = realm.where(Data.class)
                .equalTo("dayNumber", dayNumber)
                .findAll()
                .sort("time.timeFrom");
        RealmResults<Data> results1 = results.sort("day");
        List<Data> dataList = realm.copyFromRealm(results1);
        if(dataList == null)
            dataList = new ArrayList<>();
        else {
            fillDataList(dataList);
        }
        return dataList;
    }

    public List<Data> getSchedulersDataCurrentDay(int week, int value) {
        Realm realm = Realm.getDefaultInstance();
        List<Data> dataList = realm.copyFromRealm(
                realm.where(Data.class)
                        .equalTo("dayNumber", week)
                        .equalTo("day",value)
                        .sort("time.timeFrom")
                        .findAll());
        if(dataList == null) {
            dataList = new ArrayList<>();
        }
        else if(dataList.size() > 0) {
            setDataToPosition(dataList, 1);
        } else {
            dataList.add(0, new Data());
        }
        return dataList;
    }

    private void fillDataList(List<Data> dataList) {
        for(int i = 1; i< dataList.size(); i++) {
            if(!dataList.get(i).getDay().equals(dataList.get(i-1).getDay())) {
                setDataToPosition(dataList, i);
                i++;
            }
        }
        setDataToPosition(dataList, 0);
    }

    public Schedulers getSchedule() {
        Realm realm = Realm.getDefaultInstance();
        Schedulers scheduler = realm.where(Schedulers.class).findFirst();
        if(scheduler != null) return realm.copyFromRealm(scheduler);
        else return null;
    }

    public List<Security> getAllActiveTokens() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Security.class).sort("lastUsed", Sort.DESCENDING).findAll();
    }

    public void setAllActiveTokens(List<Security> tokens) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(Security.class);
            realm.insert(tokens);
        });
    }

    public void deleteActiveToken(Security token) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> token.deleteFromRealm());
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

    private void setDataToPosition(List<Data> dataList, int position) {
        Data data = new Data();
        data.setDayOfWeek(getDayOfWeek(dataList.get(position).getDay()));
        dataList.add(position, data);
    }
}
