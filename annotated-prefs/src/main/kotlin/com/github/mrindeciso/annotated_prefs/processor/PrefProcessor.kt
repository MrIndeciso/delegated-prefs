package com.github.mrindeciso.annotated_prefs.processor

import org.jetbrains.kotlin.ksp.processing.CodeGenerator
import org.jetbrains.kotlin.ksp.processing.Resolver
import org.jetbrains.kotlin.ksp.processing.SymbolProcessor
import java.io.File


class PrefProcessor : SymbolProcessor {

    lateinit var codeGenerator: CodeGenerator
    lateinit var file: File

    override fun finish() {

    }

    override fun init(
        options: Map<String, String>,
        kotlinVersion: KotlinVersion,
        codeGenerator: CodeGenerator
    ) {

    }

    override fun process(resolver: Resolver) {

    }

}

