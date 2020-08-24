package com.github.pozo.typesafepipeline.domain

import com.github.pozo.typesafepipeline.dsl.Pipeline
import com.github.pozo.typesafepipeline.dsl.Setup

object GlobalPipelineContext {
    var pipeline: Pipeline = Pipeline()
    var pipelineSetup: Setup = Setup()

}