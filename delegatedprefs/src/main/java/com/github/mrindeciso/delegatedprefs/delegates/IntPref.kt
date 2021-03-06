package com.github.mrindeciso.delegatedprefs.delegates

import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class IntPref(
    private val defaultValue: Int = 0,
    private val key: String? = null,
    private val commitInsteadOfApplying: Boolean = false
) : ReadWriteProperty<DelegatePrefInterface, Int> {

    override fun getValue(thisRef: DelegatePrefInterface, property: KProperty<*>): Int {
        val nonNullKey = key ?: property.name
        return thisRef.preferences.getInt(nonNullKey, defaultValue)
    }

    override fun setValue(thisRef: DelegatePrefInterface, property: KProperty<*>, value: Int) {
        val nonNullKey = key ?: property.name
        thisRef.preferences.edit().putInt(nonNullKey, value).apply {
            if (commitInsteadOfApplying) commit()
            else apply()
        }
    }

}