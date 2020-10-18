package com.github.mrindeciso.annotated_prefs.processor

import com.github.mrindeciso.annotated_prefs.fileModels.AnnotatedFile
import com.github.mrindeciso.annotated_prefs.processor.visitor.PreferenceVisitor
import com.github.mrindeciso.annotated_prefs.utils.typeName
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSClassifierReference
import com.google.devtools.ksp.symbol.KSTypeReference
import com.squareup.kotlinpoet.*


internal class PrefProcessor : SymbolProcessor {

    private lateinit var codeGen: CodeGenerator
    private lateinit var kspLogger: KSPLogger

    private val visitor = PreferenceVisitor()

    override fun finish() {

    }

    override fun init(
        options: Map<String, String>,
        kotlinVersion: KotlinVersion,
        codeGenerator: CodeGenerator,
        logger: KSPLogger
    ) {
        codeGen = codeGenerator
        kspLogger = logger
    }

    override fun process(resolver: Resolver) {
        resolver
            .getAllFiles()
            .map { AnnotatedFile().also { file -> it.accept(visitor, file) } }
            .filter { it.isValidClass }
            .map { data ->

                val file = codeGen.createNewFile(data.packageName ?: "", "${data.fileName}Impl")
                    .bufferedWriter()

                FileSpec.builder(data.packageName ?: "", "${data.fileName}Impl")
                    .addImport(
                        "com.github.mrindeciso.delegatedprefs.delegates",
                        "StringPref",
                        "IntPref",
                        "FloatPref",
                        "LongPref",
                        "BoolPref"
                    )
                    .addType(
                        TypeSpec.classBuilder("${data.className}Impl")
                            .addSuperinterface(ClassName.bestGuess("${data.packageName}.${data.className}"))
                            .superclass(ClassName.bestGuess("com.github.mrindeciso.delegatedprefs.implementations.DelegatedPref"))
                            .addSuperclassConstructorParameter("context")
                            .also { builder ->
                                data.prefFileName?.let {
                                    builder.addSuperclassConstructorParameter('"' + it + '"')
                                }
                            }
                            .primaryConstructor(
                                FunSpec
                                    .constructorBuilder()
                                    .addParameter(
                                        ParameterSpec(
                                            "context",
                                            ClassName.bestGuess("android.content.Context")
                                        )
                                    )
                                    .build()
                            )
                            .also { builder ->
                                data.properties.forEach { property ->
                                    builder.addProperty(
                                        PropertySpec.builder(
                                            property.name,
                                            property.type.typeName()
                                        )
                                            .addModifiers(KModifier.OVERRIDE)
                                            .mutable(property.mutable)
                                            .delegate(
                                                CodeBlock.of(
                                                    getDelegate(
                                                        property.type,
                                                        property.annotation?.key
                                                    )
                                                )
                                            )
                                            .build()
                                    )
                                }
                            }
                            .build())
                    .build()
                    .toString()
                    .let {
                        file.write(it)
                    }

                file.close()
            }
    }

    private fun getDelegate(type: KSTypeReference, key: String?) =
        when ((type.element as? KSClassifierReference)?.referencedName()?.split('.')?.last()) {
            "String" -> "StringPref()"
            "Int" -> "IntPref()"
            "Float" -> "FloatPref()"
            "Long" -> "LongPref()"
            "Boolean" -> "BoolPref()"
            else -> "error"
        }.let {
            if (key == null) {
                it
            } else {
                it.replace("()", "(key = \"$key\")")
            }
        }

}

