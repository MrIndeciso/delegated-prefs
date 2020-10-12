/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.mrindeciso.annotated_prefs.utils

import com.google.devtools.ksp.symbol.KSClassifierReference
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeReference
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName

// catch-all type name when we cannot resolve to anything.
private val UNDEFINED = ClassName("androidx.room.compiler.processing.kotlin.error", "Undefined")

/**
 * Turns a KSTypeReference into a TypeName
 *
 * We try to achieve this by first resolving it and iterating.
 * If some types cannot be resolved, we do a best effort name guess from the KSTypeReference's
 * element.
 */
internal fun KSTypeReference?.typeName(): TypeName {
    return if (this == null) {
        UNDEFINED
    } else {
        resolve().typeName() ?: fallbackClassName()
    }
}

private fun KSTypeReference.fallbackClassName(): ClassName {
    return (element as? KSClassifierReference)?.let {
        ClassName.bestGuess(it.referencedName())
    } ?: UNDEFINED
}

private fun KSDeclaration.typeName(): ClassName? {
    // if there is no qualified name, it is an error for room
    val qualified = qualifiedName?.asString() ?: return null
    // get the package name first, it might throw for invalid types, hence we use safeGetPackageName
    val pkg = safeGetPackageName().let {
        if (it == "<root>") ""
        else it
    } ?: return null
    // using qualified name and pkg, figure out the short names.
    val shortNames = if (pkg == "") {
        qualified
    } else {
        qualified.substring(pkg.length + 1)
    }.split('.')
    return ClassName(pkg, shortNames.first(), *(shortNames.drop(1).toTypedArray()))
}

private fun KSType.typeName(): TypeName? {
    return if (this.arguments.isNotEmpty()) {
        val args: Array<TypeName> = this.arguments.map {
            it.type.typeName()
        }.toTypedArray()
        val className = declaration.typeName() ?: return null
        null
    } else {
        this.declaration.typeName()
    }
}

/**
 * KSDeclaration.packageName might throw for error types.
 * https://github.com/android/kotlin/issues/121
 */
private fun KSDeclaration.safeGetPackageName(): String? {
    return try {
        packageName.asString()
    } catch (t: Throwable) {
        null
    }
}
