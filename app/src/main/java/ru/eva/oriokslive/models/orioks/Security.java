package ru.eva.oriokslive.models.orioks;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Security extends RealmObject {

    @SerializedName("last_used")
    private String lastUsed;
    @SerializedName("token")
    private String token;
    @SerializedName("user_agent")
    private String userAgent;

    public String getToken() {
        return token;
    }

    public String getLastUsed() {
        return lastUsed;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
