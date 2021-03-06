package com.github.mrindeciso.delegatedprefs.delegates

import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringSetPref(
    private val defaultValue: Set<String> = setOf(),
    private val key: String? = null,
    private val commitInsteadOfApplying: Boolean = false
) : ReadWriteProperty<DelegatePrefInterface, Set<String>> {

    override fun getValue(thisRef: DelegatePrefInterface, property: KProperty<*>): Set<String> {
        val nonNullKey = key ?: property.name
        return thisRef.preferences.getStringSet(nonNullKey, defaultValue) ?: defaultValue
    }

    override fun setValue(
        thisRef: DelegatePrefInterface,
        property: KProperty<*>,
        value: Set<String>
    ) {
        val nonNullKey = key ?: property.name
        thisRef.preferences.edit().putStringSet(nonNullKey, value).apply {
            if (commitInsteadOfApplying) commit()
            else apply()
        }
    }

}