package ru.eva.oriokslive.models.miet.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Time extends RealmObject {
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("Code")
    @Expose
    private Integer code;
    @SerializedName("TimeFrom")
    @Expose
    private String timeFrom;
    @SerializedName("TimeTo")
    @Expose
    private String timeTo;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTimeFrom() {
        return timeFrom.substring(11,16);
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo.substring(11, 16);
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

}
