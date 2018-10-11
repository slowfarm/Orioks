package ru.eva.oriokslive.models.orioks;

import com.google.gson.annotations.SerializedName;

public class AccessToken {

    @SerializedName("token")
    private String token;
    @SerializedName("error")
    private String error;

    public String getToken() {
        return token;
    }

    public String getError() {
        return error;
    }

}
