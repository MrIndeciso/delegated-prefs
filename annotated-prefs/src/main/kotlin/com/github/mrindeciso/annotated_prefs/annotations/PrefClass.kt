package com.github.mrindeciso.annotated_prefs.annotations

annotation class PrefClass(

    val prefFileName: String = "",

    val alwaysCommitInsteadOfApplying: Boolean = false

)