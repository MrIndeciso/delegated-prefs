package com.github.mrindeciso.delegatedpreferences_demo

import android.content.Context
import com.github.mrindeciso.delegatedprefs.delegates.IntPreference
import com.github.mrindeciso.delegatedprefs.impl.DelegatedPref

class PreferenceClass(ctx: Context): DelegatedPref(ctx, "mainpreferences") {

    var age: Int by IntPreference()

    var customKey: Int by IntPreference(key = "customKey")

    var defaultHigh: Int by IntPreference(defaultValue = 23)

}