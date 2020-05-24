package com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses

import android.content.Context
import com.github.mrindeciso.delegatedprefs.delegates.*
import com.github.mrindeciso.delegatedprefs.implementations.DelegatedPref
import com.github.mrindeciso.delegatedprefs_moshi.moshiSerializerOf

class BasicDemoPreference(ctx: Context) : DelegatedPref(ctx) {

    var intValue by IntPref()

    var floatValue by FloatPref()

    var longValue by LongPref()

    var stringValue by StringPref()

    var boolValue by BoolPref()

    var stringSetValue by StringSetPref()

    var custom by SerializedPref(moshiSerializerOf<String>(""))

}