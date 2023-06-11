package ru.eva.oriokslive.utils.mapper

import ru.eva.oriokslive.App
import ru.eva.oriokslive.R
import ru.eva.oriokslive.network.entity.orioks.Security
import ru.eva.oriokslive.ui.entity.SecurityItem
import ru.eva.oriokslive.utils.dateParser

fun mapSecurity(tokens: List<Security>): MutableList<SecurityItem> {
    val result = mutableListOf<SecurityItem>()
    for (security in tokens) {
        val containDevice = security.userAgent.split(" ").size > 1
        val application =
            if (containDevice) security.userAgent.substringBefore(" ") else security.userAgent
        val device =
            if (containDevice) security.userAgent.substringAfter(" ") else security.userAgent
        result.add(
            SecurityItem(
                security.token,
                App.get().getString(R.string.app, application),
                App.get().getString(R.string.device, device),
                containDevice,
                App.get().getString(R.string.last_activity, dateParser(security.lastUsed)),
            ),
        )
    }
    return result
}
