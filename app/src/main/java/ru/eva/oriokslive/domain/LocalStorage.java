package ru.eva.oriokslive.domain;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import ru.eva.oriokslive.helpers.ConvertHelper;
import ru.eva.oriokslive.models.miet.group.LocalGroup;
import ru.eva.oriokslive.models.miet.news.News;
import ru.eva.oriokslive.models.miet.schedule.Data;
import ru.eva.oriokslive.models.miet.schedule.Schedulers;
import ru.eva.oriokslive.models.orioks.AccessToken;
import ru.eva.oriokslive.models.orioks.Discipline;
import ru.eva.oriokslive.models.orioks.Event;
import ru.eva.oriokslive.models.orioks.Security;
import ru.eva.oriokslive.models.orioks.Student;

public class LocalStorage {

    private static volatile LocalStorage instance;

    public static LocalStorage getInstance() {
        if (instance == null)
            instance = new LocalStorage();
        return instance;
    }

    public void setAccessToken(AccessToken accessToken) {
        if(accessToken != null) {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(realm1 -> {
                realm.delete(AccessToken.class);
                realm.insert(accessToken);
            });
            realm.close();
        }
    }

    public String getAccessToken() {
        Realm realm = Realm.getDefaultInstance();
        AccessToken accessToken = realm.where(AccessToken.class).findFirst();
        String token = null;
        if(accessToken != null) {
            token = accessToken.getToken();
        }
        realm.close();
        return token;
    }

    public void setStudent(Student student) {
        if(student.getGroup().split("&nbsp;").length > 0)
            student.setGroup(student.getGroup().split("&nbsp;")[0]);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(Student.class);
            realm.insert(student);
        });
        realm.close();
    }

    public Student getStudent() {
        Realm realm = Realm.getDefaultInstance();
        Student student = realm.copyFromRealm(realm.where(Student.class).findFirst());
        realm.close();
        return student;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(Discipline.class);
            realm.insert(disciplines);
        });
        realm.close();
    }

    public List<Discipline> getDisciplines() {
        Realm realm = Realm.getDefaultInstance();
        List<Discipline> disciplineList = realm.copyFromRealm(realm.where(Discipline.class).findAll());
        realm.close();
        return disciplineList;
    }

    public Discipline getDiscipline(int id) {
        Realm realm = Realm.getDefaultInstance();
        Discipline discipline = realm.copyFromRealm(realm.where(Discipline.class).equalTo("id", id).findFirst());
        realm.close();
        return discipline;
    }

    public List<Event> getEventsList(int id) {
        Realm realm = Realm.getDefaultInstance();
        List<Event> eventList = realm.copyFromRealm(realm.where(Event.class).equalTo("id", id).findAll());
        realm.close();
        return eventList;
    }

    public void setEvents(List<Event> eventList) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(Event.class);
            realm.insert(eventList);
        });
        realm.close();
    }

    public void clearTables() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.deleteAll());
        realm.close();
    }

    public void setSchedulers(Schedulers schedulers) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(Schedulers.class);
            realm.delete(Data.class);
            realm.insert(schedulers);
        });
        realm.close();
    }

    public List<Data> getSchedulersDataCurrentWeek(int dayNumber, String group) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Data> results = realm.where(Data.class)
                .equalTo("dayNumber", dayNumber)
                .contains("group.name", group)
                .findAll()
                .sort("time.timeFrom");
        RealmResults<Data> results1 = results.sort("day");
        List<Data> dataList = realm.copyFromRealm(results1);
        realm.close();
        return dataList;
    }

    public List<Data> getSchedulersDataCurrentDay(int week, int value, String group) {
        Realm realm = Realm.getDefaultInstance();
        List<Data> dataList = realm.copyFromRealm(
                realm.where(Data.class)
                        .equalTo("dayNumber", week)
                        .equalTo("day",value)
                        .contains("group.name", group)
                        .sort("time.timeFrom")
                        .findAll());
        realm.close();
        return dataList;
    }

    public Schedulers getSchedule(String group) {
        Realm realm = Realm.getDefaultInstance();
        Schedulers scheduler = realm.where(Schedulers.class).equalTo("data.group.name", group).findFirst();
        if(scheduler != null) {
            scheduler = realm.copyFromRealm(scheduler);
        }
        realm.close();
        return scheduler;
    }

    public List<Security> getAllActiveTokens() {
        Realm realm = Realm.getDefaultInstance();
        List<Security> securityList = realm.copyFromRealm(realm.where(Security.class).sort("lastUsed", Sort.DESCENDING).findAll());
        realm.close();
        return securityList;
    }

    public void setAllActiveTokens(List<Security> tokens) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(Security.class);
            realm.insert(tokens);
        });
        realm.close();
    }

    public void deleteActiveToken(Security token) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            Security realmToken = realm1.where(Security.class).equalTo("token", token.getToken()).findFirst();
            if(realmToken!= null) {
                realmToken.deleteFromRealm();
            }
        });
        realm.close();
    }

    public List<News> getNews() {
        Realm realm = Realm.getDefaultInstance();
        List<News> newsList =realm.copyFromRealm(realm.where(News.class).findAll());
        realm.close();
        return newsList;
    }

    public void setNews(List<News> news) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.delete(News.class);
            realm.insert(news);
        });
        realm.close();
    }

    public List<String> getLocalGroups() {
        Realm realm = Realm.getDefaultInstance();
        LocalGroup groups = realm.where(LocalGroup.class).findFirst();
        if(groups != null) {
            groups = realm.copyFromRealm(groups);
            realm.close();
            return groups.getGroup();
        }
        realm.close();
        return new ArrayList<>();
    }

    public void addLocalGroup(String group) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            LocalGroup localGroup = realm1.where(LocalGroup.class).findFirst();
            if(localGroup != null) {
                localGroup.getGroup().add(group);
            } else {
                localGroup = new LocalGroup();
                RealmList<String> groupList = new RealmList<>();
                groupList.add(group);
                localGroup.setGroup(groupList);
                realm1.insert(localGroup);
            }
        });
        realm.close();
    }

    public void removeGroup(String group) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            LocalGroup localGroup = realm1.where(LocalGroup.class).findFirst();
            if(localGroup!= null) {
                for(int i=0 ;i< localGroup.getGroup().size(); i++) {
                    if(localGroup.getGroup().get(i).equals(group)) {
                        localGroup.getGroup().remove(i);
                        break;
                    }
                }
            }
        });
        realm.close();
    }
}
