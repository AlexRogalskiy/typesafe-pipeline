package com.github.pozo.typesafepipeline

import com.github.pozo.typesafepipeline.domain.GlobalPipelineContext

interface TypesafePipelineGenerator {

    fun generate(globalPipelineContext: GlobalPipelineContext): String
}