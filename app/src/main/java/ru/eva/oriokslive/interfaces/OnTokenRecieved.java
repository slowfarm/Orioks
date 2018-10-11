package ru.eva.oriokslive.interfaces;

import ru.eva.oriokslive.models.orioks.AccessToken;

public interface OnTokenRecieved {
    void onResponse(AccessToken accessToken);

    void onFailure(Throwable t);
}
