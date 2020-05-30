package com.github.mrindeciso.delegatedprefs_enum

import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface
import com.github.mrindeciso.delegatedprefs.interfaces.PreferenceSerializer

inline fun <reified T : Enum<T>> DelegatePrefInterface.enumSerializerOf(
    defaultValue: T
): PreferenceSerializer<T> {

    return object : PreferenceSerializer<T> {

        override fun fromString(string: String?): T {
            return if (string != null) {
                enumValueOf(string)
            } else {
                defaultValue
            }
        }

        override fun toString(obj: T): String {
            return obj.name
        }

    }

}