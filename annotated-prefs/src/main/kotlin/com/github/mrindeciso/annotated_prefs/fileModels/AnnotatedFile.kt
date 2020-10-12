package com.github.mrindeciso.annotated_prefs.fileModels

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSType

internal data class AnnotatedFile(

    var fileName: String? = null,
    var packageName: String? = null,

    var classKind: ClassKind? = null,
    var className: String? = null,

    var classType: KSType? = null,

    val properties: MutableList<AnnotatedProperty> = mutableListOf<AnnotatedProperty>(),

    )