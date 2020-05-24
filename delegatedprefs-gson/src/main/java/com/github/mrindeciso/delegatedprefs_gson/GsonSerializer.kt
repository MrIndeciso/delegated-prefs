package com.github.mrindeciso.delegatedprefs_gson

import com.github.mrindeciso.delegatedprefs.interfaces.PreferenceSerializer
import com.google.gson.Gson
import java.lang.reflect.Type

class GsonSerializer<T>(
    private val gson: Gson,
    private val type: Type,
    private val defaultValue: T
) : PreferenceSerializer<T> {

    constructor(type: Type, defaultValue: T) : this(Gson(), type, defaultValue)

    override fun fromString(string: String?): T {
        return if (string != null) {
            gson.fromJson(string, type)
        } else {
            defaultValue
        }
    }

    override fun toString(obj: T): String {
        return gson.toJson(obj, type)
    }

}