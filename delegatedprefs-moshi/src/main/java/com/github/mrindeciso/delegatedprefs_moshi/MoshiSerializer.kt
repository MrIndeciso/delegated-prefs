package com.github.mrindeciso.delegatedprefs_moshi

import com.github.mrindeciso.delegatedprefs.interfaces.PreferenceSerializer
import com.squareup.moshi.JsonAdapter

class MoshiSerializer<T>(
    private val adapter: JsonAdapter<T>,
    private val defaultValue: T
) : PreferenceSerializer<T> {

    override fun fromString(string: String?): T {
        return if (string != null) {
            adapter.fromJson(string) ?: defaultValue
        } else {
            defaultValue
        }
    }

    override fun toString(obj: T): String {
        return adapter.toJson(obj)
    }

}