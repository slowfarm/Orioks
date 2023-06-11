package ru.eva.oriokslive.utils.mapper

import ru.eva.oriokslive.App
import ru.eva.oriokslive.R
import ru.eva.oriokslive.network.entity.orioks.Resit
import ru.eva.oriokslive.ui.entity.ResitItem
import ru.eva.oriokslive.utils.resitDateParser

fun mapResits(resits: List<Resit>) = resits.map {
    ResitItem(
        id = it.id,
        room = App.get().getString(R.string.room, it.classroom),
        datetime = App.get().getString(R.string.resit_date, resitDateParser(it.datetime)),
        resitNumber = it.resitNumber,
    )
}
