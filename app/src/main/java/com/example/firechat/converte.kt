package com.example.firechat

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class converte {
    @TypeConverter
    fun fromSource(a: List<Chat>): String {
        val listtype = object : TypeToken<List<Chat>>() {}.rawType

        val json = Gson().toJson(a, listtype)

        return json

    }

    @TypeConverter
    fun tvSource(a: String): List<Chat> {
        val type = object : TypeToken<List<Chat>?>() {}.type
        return Gson().fromJson(a, type)
    }
}