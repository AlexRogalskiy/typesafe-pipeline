package com.github.pozo.typesafepipeline

import com.github.pozo.typesafepipeline.dsl.Pipeline

interface TypesafePipelineSerializer {

    fun serialize(pipeline: Pipeline): String

    fun suggestedExtension(): String

    fun suggestedFileName(): String

    fun suggestedFileNameWithExtension(): String
}