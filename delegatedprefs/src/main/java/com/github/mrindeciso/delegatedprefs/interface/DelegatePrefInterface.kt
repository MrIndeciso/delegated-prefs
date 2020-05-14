package com.github.mrindeciso.delegatedprefs.`interface`

import android.content.SharedPreferences

interface DelegatePrefInterface {

    val preferences: SharedPreferences

    companion object {

        const val DEFAULT_PREFERENCE_NAME = "preferences"

    }

}