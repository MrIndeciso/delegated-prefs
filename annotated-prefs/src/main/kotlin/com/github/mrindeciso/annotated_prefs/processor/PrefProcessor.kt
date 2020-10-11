package com.github.mrindeciso.annotated_prefs.processor

import com.github.mrindeciso.annotated_prefs.annotations.PrefClass
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor


class PrefProcessor : SymbolProcessor {

    lateinit var codeGen: CodeGenerator

    override fun finish() {

    }

    override fun init(
        options: Map<String, String>,
        kotlinVersion: KotlinVersion,
        codeGenerator: CodeGenerator,
        logger: KSPLogger
    ) {
        codeGen = codeGenerator
    }

    override fun process(resolver: Resolver) {
        val annotatedClasses = resolver.getSymbolsWithAnnotation(PrefClass::class.java.name)
        println(annotatedClasses)
        /*annotatedClasses.filterIsInstance<KSClassDeclaration>()
            .forEach { it.accept(PrefClassBuilder(codeGen), Unit) }*/
    }

}

