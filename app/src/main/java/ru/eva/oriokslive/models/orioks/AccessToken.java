package ru.eva.oriokslive.models.orioks;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class AccessToken extends RealmObject {

    @SerializedName("token")
    private String token;
    @SerializedName("error")
    private String error;
    @SerializedName("info")
    private String info;

    public String getToken() {
        return token;
    }

    public String getError() {
        return error;
    }

    public String getInfo() {
        return info;
    }

}
