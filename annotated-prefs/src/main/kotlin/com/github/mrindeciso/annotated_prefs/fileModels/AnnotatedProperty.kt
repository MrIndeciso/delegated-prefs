package com.github.mrindeciso.annotated_prefs.fileModels

import com.google.devtools.ksp.symbol.KSTypeReference

internal data class AnnotatedProperty(

    val name: String,

    val mutable: Boolean,

    val type: KSTypeReference,

    val delegated: Boolean,

    val annotation: PrefAnnotation? = null

)