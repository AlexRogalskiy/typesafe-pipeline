package com.github.pozo.typesafepipeline.gitlab

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.github.pozo.typesafepipeline.TypesafePipelineSerializer
import com.github.pozo.typesafepipeline.dsl.Pipeline


open class GitlabPipelineSerializer : TypesafePipelineSerializer {

    private val mapper = ObjectMapper(YAMLFactory());


    override fun serialize(pipeline: Pipeline): String {
        return mapper.writeValueAsString(pipeline)
    }

    override fun suggestedExtension(): String {
        return "yml"
    }

    override fun suggestedFileName(): String {
        return ".gitlab-ci"
    }

    override fun suggestedFileNameWithExtension(): String {
        return "${suggestedFileName()}.${suggestedExtension()}"
    }
}