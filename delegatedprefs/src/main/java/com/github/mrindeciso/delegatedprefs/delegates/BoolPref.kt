package com.github.mrindeciso.delegatedprefs.delegates

import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class BoolPref(
    private val defaultValue: Boolean = false,
    private val key: String? = null,
    private val commitInsteadOfApplying: Boolean = false
) : ReadWriteProperty<DelegatePrefInterface, Boolean> {

    override fun getValue(thisRef: DelegatePrefInterface, property: KProperty<*>): Boolean {
        val nonNullKey = key ?: property.name
        return thisRef.preferences.getBoolean(nonNullKey, defaultValue)
    }

    override fun setValue(thisRef: DelegatePrefInterface, property: KProperty<*>, value: Boolean) {
        val nonNullKey = key ?: property.name
        thisRef.preferences.edit().putBoolean(nonNullKey, value).apply {
            if (commitInsteadOfApplying) commit()
            else apply()
        }
    }

}