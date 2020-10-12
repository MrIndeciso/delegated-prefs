package com.github.mrindeciso.annotated_prefs.annotations

annotation class Pref(

    val key: String = "",

    val commitInsteadOfApplying: Boolean = false

)