package ru.eva.oriokslive.models.miet.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Clazz extends RealmObject {
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("TeacherFull")
    @Expose
    private String teacherFull;
    @SerializedName("Teacher")
    @Expose
    private String teacher;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherFull() {
        return teacherFull;
    }

    public void setTeacherFull(String teacherFull) {
        this.teacherFull = teacherFull;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}
