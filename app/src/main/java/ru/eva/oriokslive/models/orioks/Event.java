package ru.eva.oriokslive.models.orioks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Event extends RealmObject {
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("current_grade")
    @Expose
    private Double currentGrade;
    @SerializedName("max_grade")
    @Expose
    private Double maxGrade;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("week")
    @Expose
    private String week;
    @SerializedName("id")
    @Expose
    private Integer id;
    private String currentGradeValue;
    private String maxGradeValue;
    private int progress;
    private int color;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Double getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(Double currentGrade) {
        this.currentGrade = currentGrade;
    }

    public Double getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(Double maxGrade) {
        this.maxGrade = maxGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCurrentGradeValue() {
        return currentGradeValue;
    }

    public void setCurrentGradeValue(String currentGradeValue) {
        this.currentGradeValue = currentGradeValue;
    }

    public String getMaxGradeValue() {
        return maxGradeValue;
    }

    public void setMaxGradeValue(String maxGradeValue) {
        this.maxGradeValue = maxGradeValue;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}