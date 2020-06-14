package com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses

import com.github.mrindeciso.annotated_prefs.annotations.PrefClass

@PrefClass
interface AnnotatedPreference {

    var intValue: Int

    var floatValue: Float

    var longValue: Long

    var stringValue: String

    var boolValue: Boolean

}