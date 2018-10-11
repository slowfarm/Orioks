package ru.eva.oriokslive.models.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

import io.realm.RealmObject;

public class Schedulers extends RealmObject {
    @SerializedName("Data")
    @Expose
    private RealmList<Data> data;
    @SerializedName("Semestr")
    @Expose
    private String semestr;

    public RealmList<Data> getData() {
        return data;
    }

    public void setData(RealmList<Data> data) {
        this.data = data;
    }

    public String getSemestr() {
        return semestr;
    }

    public void setSemestr(String semestr) {
        this.semestr = semestr;
    }
}
