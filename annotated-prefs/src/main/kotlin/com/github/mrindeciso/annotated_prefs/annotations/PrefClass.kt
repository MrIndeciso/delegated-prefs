package com.github.mrindeciso.annotated_prefs.annotations

annotation class PrefClass(

    val prefFileName: String = "",

    val commitInsteadOfApplying: Boolean = false

)