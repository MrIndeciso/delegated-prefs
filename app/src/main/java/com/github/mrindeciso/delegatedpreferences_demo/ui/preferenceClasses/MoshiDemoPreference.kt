package com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses

import android.content.Context
import com.github.mrindeciso.delegatedprefs.delegates.SerializedPref
import com.github.mrindeciso.delegatedprefs.implementations.DelegatedPref
import com.github.mrindeciso.delegatedprefs_moshi.moshiSerializerOf
import com.squareup.moshi.JsonClass

class MoshiDemoPreference(ctx: Context) : DelegatedPref(ctx) {

    var userData by SerializedPref(moshiSerializerOf(MoshiUserData()), key = "moshiUserData")

}

@JsonClass(generateAdapter = true)
data class MoshiUserData(
    val username: String,
    val password: String,
    val acceptedPrivacySettings: Boolean
) {

    constructor() : this("", "", false)

}