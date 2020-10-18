package com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses

import com.github.mrindeciso.annotated_prefs.annotations.Pref
import com.github.mrindeciso.annotated_prefs.annotations.PrefClass

@PrefClass(prefFileName = "provola")
interface AnnotatedPreference {

    @Pref(key = "provolone")
    var intValue: Int

    var floatValue: Float

    var longValue: Long

    var stringValue: String

    var boolValue: Boolean

}