package ru.eva.oriokslive.domain.provider

import android.content.Context
import ru.eva.oriokslive.domain.GeneralDatabase

interface DatabaseProvider {

    fun provideDatabase(context: Context): GeneralDatabase
}
