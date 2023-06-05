package ru.eva.oriokslive.di.domain

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.eva.oriokslive.domain.GeneralDatabase
import ru.eva.oriokslive.domain.dao.DisciplinesDao
import ru.eva.oriokslive.domain.dao.EventDao
import ru.eva.oriokslive.domain.dao.ScheduleDao
import ru.eva.oriokslive.domain.dao.StudentDao
import ru.eva.oriokslive.domain.provider.DatabaseProvider
import ru.eva.oriokslive.domain.provider.GsonProvider
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    private const val SETTINGS_PREFERENCES = "SETTINGS_PREFERENCES"

    @Provides
    fun provideStudentDao(database: GeneralDatabase): StudentDao = database.getStudentDao()

    @Provides
    fun provideDisciplineDao(database: GeneralDatabase): DisciplinesDao = database.getDisciplineDao()

    @Provides
    fun provideScheduleDao(database: GeneralDatabase): ScheduleDao = database.getScheduleDao()

    @Provides
    fun provideEventDao(database: GeneralDatabase): EventDao = database.getEventDao()

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE)

    @Provides
    fun provideGson(gsonProvider: GsonProvider): Gson = gsonProvider.provideGson()

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        databaseProvider: DatabaseProvider,
    ): GeneralDatabase = databaseProvider.provideDatabase(context)
}
