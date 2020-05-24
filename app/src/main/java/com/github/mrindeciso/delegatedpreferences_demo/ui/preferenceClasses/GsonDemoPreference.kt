package com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses

import android.content.Context
import com.github.mrindeciso.delegatedprefs.delegates.SerializedPref
import com.github.mrindeciso.delegatedprefs.implementations.DelegatedPref
import com.github.mrindeciso.delegatedprefs_gson.gsonSerializerOf

class GsonDemoPreference(ctx: Context) : DelegatedPref(ctx) {

    var userData by SerializedPref(gsonSerializerOf(GsonUserData()))

}

data class GsonUserData(
    val username: String,
    val password: String,
    val acceptedPrivacySettings: Boolean
) {

    constructor() : this("", "", false)

}