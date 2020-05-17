@file:Suppress("ClassName")

package com.github.mrindeciso.delegatedprefs

import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * A generic [pref] which handles every possible type
 *
 * I personally don't recommend using this class, but it works so...
 *
 * @param [T] the type which needs to be stored
 * @property [type] the class type, needed because we can't do reflection without kotlin-reflect
 * @property [defaultValue] the default value of type [T]
 * @property [key] the item key if the name of the property isn't enough
 * @property [commitInsteadOfApplying] commits the change instead of storing it in memory and
 * handling it in background. Let it set to false if you don't know why you would need to change it
 */
class pref<T : Any>(
    private val type: KClass<T>,
    private val defaultValue: T? = null,
    private val key: String? = null,
    private val commitInsteadOfApplying: Boolean = false
) : ReadWriteProperty<DelegatePrefInterface, T> {

    override fun getValue(thisRef: DelegatePrefInterface, property: KProperty<*>): T {
        val nonNullKey = key ?: property.name
        return when (type) {
            String::class -> {
                thisRef.preferences.getString(nonNullKey, defaultValue as String? ?: "") as T
            }
            Int::class -> {
                thisRef.preferences.getInt(nonNullKey, defaultValue as Int? ?: 0) as T
            }
            Long::class -> {
                thisRef.preferences.getLong(nonNullKey, defaultValue as Long? ?: 0) as T
            }
            Float::class -> {
                thisRef.preferences.getFloat(nonNullKey, defaultValue as Float? ?: 0f) as T
            }
            Boolean::class -> {
                thisRef.preferences.getBoolean(nonNullKey, defaultValue as Boolean? ?: false) as T
            }
            Set::class -> {
                thisRef.preferences.getStringSet(
                    nonNullKey,
                    defaultValue as Set<String>? ?: setOf()
                ) as T
            }
            else -> {
                throw RuntimeException("Return type $type is not a primitive")
            }
        }
    }

    override fun setValue(thisRef: DelegatePrefInterface, property: KProperty<*>, value: T) {
        val nonNullKey = key ?: property.name
        thisRef.preferences.edit().apply {
            when (type) {
                String::class -> putString(nonNullKey, value as String?)
                Int::class -> putInt(nonNullKey, value as Int? ?: 0)
                Long::class -> putLong(nonNullKey, value as Long? ?: 0)
                Float::class -> putFloat(nonNullKey, value as Float? ?: 0f)
                Boolean::class -> putBoolean(nonNullKey, value as Boolean? ?: false)
                Set::class -> putStringSet(nonNullKey, value as Set<String>? ?: setOf())
                else -> {
                    throw RuntimeException("Return type $type is not a primitive")
                }
            }
            if (commitInsteadOfApplying) commit()
            else apply()
        }
    }

}