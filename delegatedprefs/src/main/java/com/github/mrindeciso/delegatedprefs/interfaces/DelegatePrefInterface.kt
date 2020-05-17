package com.github.mrindeciso.delegatedprefs.interfaces

import android.content.SharedPreferences

/**
 * The base interface extended by every implementation
 *
 * An interface that every implementation of the base Delegates Preference class should use in order
 * to make the delegates work.
 *
 * Using this interface we can make the code cleaner, because we don't need to pass a reference to
 * every delegate
 */
interface DelegatePrefInterface {

    /**
     * This property must be used by every delegate as the shared preferences instance
     */
    val preferences: SharedPreferences

    companion object {

        const val DEFAULT_PREFERENCE_NAME = "preferences"

    }

}