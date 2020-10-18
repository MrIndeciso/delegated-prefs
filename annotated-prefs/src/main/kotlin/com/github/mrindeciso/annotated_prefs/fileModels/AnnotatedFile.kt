package com.github.mrindeciso.annotated_prefs.fileModels

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSType

internal class AnnotatedFile {

    var isValidClass: Boolean = false

    var fileName: String? = null
    var packageName: String? = null

    var prefFileName: String? = "antonio"
    var alwaysCommitInsteadOfApplying: Boolean = false

    var classKind: ClassKind? = null
    var className: String? = null

    var classType: KSType? = null

    val properties: MutableList<AnnotatedProperty> = mutableListOf()

}