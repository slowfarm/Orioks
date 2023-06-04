package ru.eva.oriokslive.domain.provider

import android.content.Context
import androidx.room.Room
import ru.eva.oriokslive.domain.Common
import ru.eva.oriokslive.domain.GeneralDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseProviderImpl @Inject constructor() : DatabaseProvider {

    override fun provideDatabase(context: Context): GeneralDatabase =
        Room.databaseBuilder(
            context,
            GeneralDatabase::class.java,
            Common.DATABASE_NAME,
        )
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .addMigrations(*Common.getMigrations())
            .build()
}
