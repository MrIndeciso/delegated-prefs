package com.github.mrindeciso.annotated_prefs.processor.visitor

import com.github.mrindeciso.annotated_prefs.fileModels.AnnotatedFile
import com.github.mrindeciso.annotated_prefs.fileModels.AnnotatedProperty
import com.github.mrindeciso.annotated_prefs.fileModels.PrefAnnotation
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.visitor.KSEmptyVisitor

internal class PreferenceVisitor : KSEmptyVisitor<AnnotatedFile, Unit>() {

    override fun defaultHandler(node: KSNode, data: AnnotatedFile) {

    }

    override fun visitAnnotation(annotation: KSAnnotation, data: AnnotatedFile) {
        if (annotation.shortName.asString().contains("PrefClass")) {
            data.isValidClass = true
        }
    }

    override fun visitFile(file: KSFile, data: AnnotatedFile) {
        data.fileName = file.fileName.removeSuffix(".kt").removeSuffix(".java")
        data.packageName = file.packageName.asString()

        file.declarations.map { it.accept(this, data) }
    }

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: AnnotatedFile) {
        classDeclaration.annotations.map { it.accept(this, data) }

        /*classDeclaration.annotations.first().let {
            it.arguments.find { it.name?.asString() == "prefFileName" }?.let {
                data.prefFileName = it.value as? String
            }
        }*/

        data.classKind = classDeclaration.classKind
        data.className = classDeclaration.simpleName.asString()

        classDeclaration.getAllProperties().map { it.accept(this, data) }
    }

    override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: AnnotatedFile) {
        val pref = if (property.annotations.isEmpty()) {
            null
        } else {
            property.annotations.find { it.shortName.asString().contains("Pref") }?.let {
                val key = it.arguments.find { it.name?.asString() == "key" }?.value as? String
                val commit =
                    it.arguments.find { it.name?.asString() == "commitInsteadOfApplying" }?.value as? Boolean

                PrefAnnotation(key, commit)
            }
        }

        val annotatedProperty = AnnotatedProperty(
            property.simpleName.asString(),
            property.isMutable,
            property.type,
            property.isDelegated(),
            pref
        )

        data.properties.add(annotatedProperty)
    }

}