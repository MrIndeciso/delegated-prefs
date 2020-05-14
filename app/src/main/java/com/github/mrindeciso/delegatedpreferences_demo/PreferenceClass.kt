package com.github.mrindeciso.delegatedpreferences_demo

import android.content.Context
import com.github.mrindeciso.delegatedprefs.delegates.IntPref
import com.github.mrindeciso.delegatedprefs.impl.DelegatedPref

class PreferenceClass(ctx: Context): DelegatedPref(ctx, "mainpreferences") {

    var age: Int by IntPref()

    var customKey: Int by IntPref(key = "customKey")

    var defaultHigh: Int by IntPref(defaultValue = 23)

}