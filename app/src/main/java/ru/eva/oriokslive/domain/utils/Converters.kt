package ru.eva.oriokslive.domain.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromStringToList(string: String): List<String> = if (string.isEmpty()) {
        emptyList()
    } else {
        Gson().fromJson(string, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromListToString(list: List<String>?): String = list?.let { Gson().toJson(list) } ?: ""
}
