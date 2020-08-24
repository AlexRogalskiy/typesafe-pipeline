package com.github.pozo.typesafepipeline

import com.github.pozo.TypesafePipelineParser
import com.github.pozo.typesafepipeline.generator.TypesafePipelineGeneratorDefault
import java.io.File

fun main() {
    val pipelineGenerator: TypesafePipelineGenerator = TypesafePipelineGeneratorDefault()
    val pipelineParser = TypesafePipelineParser()

    val scriptContent =
        readFileDirectlyAsText("Main.kts")

    val pipelineContext = pipelineParser.parse(scriptContent)
    val generatedPipeline = pipelineGenerator.generate(pipelineContext)

    println("generatedPipeline = $generatedPipeline")

}

fun readFileDirectlyAsText(fileName: String): String = File(fileName).readText(Charsets.UTF_8)