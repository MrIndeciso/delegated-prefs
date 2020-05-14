package com.github.mrindeciso.delegatedprefs.`interface`

interface PreferenceSerializer<T> {

    fun toString(obj: T): String

    fun fromString(string: String?): T

}