package ru.eva.oriokslive.activities.Registration;

import ru.eva.oriokslive.helpers.RetrofitHelper;
import ru.eva.oriokslive.helpers.StorageHelper;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.AccessToken;

public class Repository implements Contract.Repository {
    @Override
    public String getAccessToken() {
        return StorageHelper.getInstance().getAccessToken();
    }

    @Override
    public void getAccessToken(String encodedString, OnTokenRecieved onTokenRecieved) {
        RetrofitHelper.getInstance().setOnTokenReceived(onTokenRecieved);
        RetrofitHelper.getInstance().getAccessToken(encodedString);
    }

    @Override
    public void setAccessToken(AccessToken accessToken) {
        StorageHelper.getInstance().setAccessToken(accessToken);
    }
}
