package com.github.mrindeciso.annotated_prefs.processor.visitor

import com.squareup.kotlinpoet.*
import org.jetbrains.kotlin.ksp.getDeclaredProperties
import org.jetbrains.kotlin.ksp.processing.CodeGenerator
import org.jetbrains.kotlin.ksp.symbol.KSClassDeclaration
import org.jetbrains.kotlin.ksp.symbol.KSPropertyDeclaration
import org.jetbrains.kotlin.ksp.symbol.KSVisitorVoid

internal class PrefClassBuilder(
    private val codeGenerator: CodeGenerator
) : KSVisitorVoid() {

    private lateinit var prefClass: TypeSpec.Builder

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        val packageName = classDeclaration.containingFile?.packageName?.asString() ?: ""

        val generatedFile = codeGenerator.createNewFile(
            packageName,
            "${classDeclaration.simpleName.asString()}Impl"
        )

        prefClass = TypeSpec.classBuilder("${classDeclaration.simpleName.asString()}Impl")
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addParameter("context", ClassName("android.content", "Context"))
                    .build()
            )
            .addSuperinterface(ClassName(packageName, classDeclaration.simpleName.asString()))
            .superclass(
                ClassName(
                    "com.github.mrindeciso.delegatedprefs.implementations",
                    "DelegatedPref"
                )
            )
            .addSuperclassConstructorParameter("context")

        classDeclaration.getDeclaredProperties().forEach {
            it.accept(this, Unit)
        }

        FileSpec.builder(packageName, "${classDeclaration.simpleName.asString()}Impl")
            .addImport(
                "com.github.mrindeciso.delegatedprefs.delegates",
                "IntPref",
                "StringPref",
                "BoolPref",
                "FloatPref",
                "LongPref",
                "StringSetPref"
            )
            .addType(
                prefClass.build()
            ).build().toString().let {
                generatedFile.writeText(it)
            }
    }

    override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: Unit) {
        val declaration = property.type?.resolve()?.declaration ?: return

        val type = declaration.qualifiedName?.asString() ?: ""

        val delegate = when (type.split(".").last()) {
            "String" -> "StringPref()"
            "Int" -> "IntPref()"
            "Boolean" -> "BoolPref()"
            "Float" -> "FloatPref()"
            "Long" -> "LongPref()"
            "Set" -> "StringSetPref()"
            else -> ""
        }

        prefClass.addProperty(
            PropertySpec.builder(
                property.simpleName.asString(),
                ClassName("", type),
                KModifier.OVERRIDE
            )
                .mutable(true)
                .delegate(delegate)
                .build()
        )
    }

}