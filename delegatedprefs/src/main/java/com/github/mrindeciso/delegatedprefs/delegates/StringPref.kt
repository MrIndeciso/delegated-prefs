package com.github.mrindeciso.delegatedprefs.delegates

import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringPref(
    private val defaultValue: String = "",
    private val key: String? = null
) : ReadWriteProperty<DelegatePrefInterface, String> {

    override fun getValue(thisRef: DelegatePrefInterface, property: KProperty<*>): String {
        val nonNullKey = key ?: property.name
        return thisRef.preferences.getString(nonNullKey, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: DelegatePrefInterface, property: KProperty<*>, value: String) {
        val nonNullKey = key ?: property.name
        thisRef.preferences.edit().putString(nonNullKey, value).apply()
    }

}