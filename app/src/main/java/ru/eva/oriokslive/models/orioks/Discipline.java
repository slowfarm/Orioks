package ru.eva.oriokslive.models.orioks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Discipline extends RealmObject {

    @SerializedName("control_form")
    @Expose
    private String controlForm;
    @SerializedName("current_grade")
    @Expose
    private Double currentGrade;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("exam_date")
    @Expose
    private String examDate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("max_grade")
    @Expose
    private Double maxGrade;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("teachers")
    @Expose
    private RealmList<String> teachers = null;
    private String currentGradeValue;
    private String maxGradeValue;
    private int progress;
    private int color;

    public String getControlForm() {
        return controlForm;
    }

    public void setControlForm(String controlForm) {
        this.controlForm = controlForm;
    }

    public Double getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(Double currentGrade) {
        this.currentGrade = currentGrade;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public RealmList<String> getTeachers() {
        return teachers;
    }

    public void setTeachers(RealmList<String> teachers) {
        this.teachers = teachers;
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