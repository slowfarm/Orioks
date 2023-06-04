package ru.eva.oriokslive.domain.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.eva.oriokslive.network.entity.schedule.Clazz
import ru.eva.oriokslive.network.entity.schedule.Group
import ru.eva.oriokslive.network.entity.schedule.Room
import ru.eva.oriokslive.network.entity.schedule.Time


class Converters {
    @TypeConverter
    fun fromStringToList(string: String): List<String> =
        Gson().fromJson(string, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun fromListToString(list: List<String>): String = Gson().toJson(list)

//    @TypeConverter
//    fun fromStringToTime(string: String): Time =
//        Gson().fromJson(string, object : TypeToken<Time>() {}.type)
//
//    @TypeConverter
//    fun fromTimeToString(time: Time): String = Gson().toJson(time)
//
//    @TypeConverter
//    fun fromStringToClazz(string: String): Clazz =
//        Gson().fromJson(string, object : TypeToken<Clazz>() {}.type)
//
//    @TypeConverter
//    fun fromClazzToString(clazz: Clazz): String = Gson().toJson(clazz)
//
//    @TypeConverter
//    fun fromStringToGroup(string: String): Group =
//        Gson().fromJson(string, object : TypeToken<Group>() {}.type)
//
//    @TypeConverter
//    fun fromGroupToString(group: Group): String = Gson().toJson(group)
//
//    @TypeConverter
//    fun fromStringToRoom(string: String): Room =
//        Gson().fromJson(string, object : TypeToken<Room>() {}.type)
//
//    @TypeConverter
//    fun fromRoomToString(room: Room): String = Gson().toJson(room)
}
