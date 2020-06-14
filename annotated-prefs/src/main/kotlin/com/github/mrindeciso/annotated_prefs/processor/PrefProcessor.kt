package com.github.mrindeciso.annotated_prefs.processor

import com.github.mrindeciso.annotated_prefs.annotations.PrefClass
import com.github.mrindeciso.annotated_prefs.processor.visitor.PrefClassBuilder
import org.jetbrains.kotlin.ksp.processing.CodeGenerator
import org.jetbrains.kotlin.ksp.processing.Resolver
import org.jetbrains.kotlin.ksp.processing.SymbolProcessor
import org.jetbrains.kotlin.ksp.symbol.KSClassDeclaration


class PrefProcessor : SymbolProcessor {

    lateinit var codeGen: CodeGenerator

    override fun finish() {

    }

    override fun init(
        options: Map<String, String>,
        kotlinVersion: KotlinVersion,
        codeGenerator: CodeGenerator
    ) {
        codeGen = codeGenerator
    }

    override fun process(resolver: Resolver) {
        val annotatedClasses = resolver.getSymbolsWithAnnotation(PrefClass::class.java.name)
        println(annotatedClasses)
        annotatedClasses.filterIsInstance<KSClassDeclaration>()
            .forEach { it.accept(PrefClassBuilder(codeGen), Unit) }
    }

}

