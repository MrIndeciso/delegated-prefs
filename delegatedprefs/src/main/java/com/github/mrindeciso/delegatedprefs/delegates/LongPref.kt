package com.github.mrindeciso.delegatedprefs.delegates

import com.github.mrindeciso.delegatedprefs.`interface`.DelegatePrefInterface
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LongPref(
    private val defaultValue: Long = 0,
    private val key: String? = null
) : ReadWriteProperty<DelegatePrefInterface, Long> {

    override fun getValue(thisRef: DelegatePrefInterface, property: KProperty<*>): Long {
        val nonNullKey = key ?: property.name
        return thisRef.preferences.getLong(nonNullKey, defaultValue)
    }

    override fun setValue(thisRef: DelegatePrefInterface, property: KProperty<*>, value: Long) {
        val nonNullKey = key ?: property.name
        thisRef.preferences.edit().putLong(nonNullKey, value).apply()
    }

}