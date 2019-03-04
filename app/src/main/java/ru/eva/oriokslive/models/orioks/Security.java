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
    private String application;
    private String device;
    private boolean containDevice;
    private String lastUsedValue;

    public String getToken() {
        return token;
    }

    public String getLastUsed() {
        return lastUsed;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public boolean isContainDevice() {
        return containDevice;
    }

    public void setContainDevice(boolean containDevice) {
        this.containDevice = containDevice;
    }

    public String getLastUsedValue() {
        return lastUsedValue;
    }

    public void setLastUsedValue(String lastUsedValue) {
        this.lastUsedValue = lastUsedValue;
    }
}
