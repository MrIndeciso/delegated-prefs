package com.github.mrindeciso.delegatedprefs.delegates

import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface
import com.github.mrindeciso.delegatedprefs.interfaces.PreferenceSerializer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SerializedPref<T>(
    private val serializer: PreferenceSerializer<T>,
    private val key: String? = null,
    private val commitInsteadOfApplying: Boolean = false
) : ReadWriteProperty<DelegatePrefInterface, T> {

    override fun getValue(thisRef: DelegatePrefInterface, property: KProperty<*>): T {
        val nonNullKey = key ?: property.name
        return serializer.fromString(thisRef.preferences.getString(nonNullKey, null))
    }

    override fun setValue(thisRef: DelegatePrefInterface, property: KProperty<*>, value: T) {
        val nonNullKey = key ?: property.name
        thisRef.preferences.edit().putString(nonNullKey, serializer.toString(value)).apply {
            if (commitInsteadOfApplying) commit()
            else apply()
        }
    }

}