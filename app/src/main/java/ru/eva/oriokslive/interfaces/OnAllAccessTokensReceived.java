package ru.eva.oriokslive.interfaces;

import java.util.List;

import ru.eva.oriokslive.models.orioks.Security;

public interface OnAllAccessTokensReceived {
    void onResponse(List<Security> tokens);

    void onFailure(Throwable t);
}
