package com.github.mrindeciso.delegatedprefs.interfaces

/**
 * This interface should be implemented when custom data classes must be serialized into preferences
 *
 * @param T the type of the serialized element
 */
interface PreferenceSerializer<T> {

    /**
     * Serializes [obj] of type [T] into a non-nullable String
     * @return the serialized String
     */
    fun toString(obj: T): String

    /**
     * Deserializes the [string] back into an object of type [T]
     * Note: [string] is nullable, in which case the implementation should return the default value
     * @return the object of type [T]
     */
    fun fromString(string: String?): T

}