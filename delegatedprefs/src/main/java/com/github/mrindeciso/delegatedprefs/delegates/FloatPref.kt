package com.github.mrindeciso.delegatedprefs.delegates

import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FloatPref(
    private val defaultValue: Float = 0f,
    private val key: String? = null,
    private val commitInsteadOfApplying: Boolean = false
) : ReadWriteProperty<DelegatePrefInterface, Float> {

    override fun getValue(thisRef: DelegatePrefInterface, property: KProperty<*>): Float {
        val nonNullKey = key ?: property.name
        return thisRef.preferences.getFloat(nonNullKey, defaultValue)
    }

    override fun setValue(thisRef: DelegatePrefInterface, property: KProperty<*>, value: Float) {
        val nonNullKey = key ?: property.name
        thisRef.preferences.edit().putFloat(nonNullKey, value).apply {
            if (commitInsteadOfApplying) commit()
            else apply()
        }
    }

}