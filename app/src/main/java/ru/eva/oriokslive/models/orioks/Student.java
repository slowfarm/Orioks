package ru.eva.oriokslive.models.orioks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Student extends RealmObject {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("course")
    @Expose
    private Integer course;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("record_book_id")
    @Expose
    private String recordBookId;
    @SerializedName("semester")
    @Expose
    private Integer semester;
    @SerializedName("study_direction")
    @Expose
    private String studyDirection;
    @SerializedName("study_profile")
    @Expose
    private String studyProfile;
    @SerializedName("year")
    @Expose
    private String year;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCourse() {
        return String.valueOf(course);
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRecordBookId() {
        return recordBookId;
    }

    public void setRecordBookId(String recordBookId) {
        this.recordBookId = recordBookId;
    }

    public String getSemester() {
        return String.valueOf(semester);
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getStudyDirection() {
        return studyDirection;
    }

    public void setStudyDirection(String studyDirection) {
        this.studyDirection = studyDirection;
    }

    public String getStudyProfile() {
        return studyProfile;
    }

    public void setStudyProfile(String studyProfile) {
        this.studyProfile = studyProfile;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}