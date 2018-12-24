package ru.eva.oriokslive.models.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Data extends RealmObject {
    @SerializedName("Day")
    @Expose
    private Integer day;
    @SerializedName("DayNumber")
    @Expose
    private Integer dayNumber;
    @SerializedName("Time")
    @Expose
    private Time time;
    @SerializedName("Class")
    @Expose
    private Clazz clazz;
    @SerializedName("Group")
    @Expose
    private Group group;
    @SerializedName("Room")
    @Expose
    private Room room;

    private String dayOfWeek;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

}
