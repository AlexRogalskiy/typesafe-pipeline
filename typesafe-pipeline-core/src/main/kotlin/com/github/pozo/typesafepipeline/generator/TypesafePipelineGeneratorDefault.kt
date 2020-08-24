package com.github.pozo.typesafepipeline.generator

import com.github.pozo.typesafepipeline.TypesafePipelineGenerator
import com.github.pozo.typesafepipeline.TypesafePipelineSerializer
import com.github.pozo.typesafepipeline.domain.GlobalPipelineContext
import java.util.ServiceLoader

class TypesafePipelineGeneratorDefault : TypesafePipelineGenerator {

    override fun generate(globalPipelineContext: GlobalPipelineContext): String {
        val pipelineSerializer = getSerializerForTargetPipeline(globalPipelineContext)

        if (pipelineSerializer == null) {
            throw IllegalStateException("No 'TypesafePipelineSerializer' implementation found for ${globalPipelineContext.pipelineSetup.target}")
        } else {
            return pipelineSerializer.serialize(globalPipelineContext.pipeline)
        }
    }

    private fun getSerializerForTargetPipeline(globalPipelineContext: GlobalPipelineContext): TypesafePipelineSerializer? {
        val targetCiPipeline = globalPipelineContext.pipelineSetup.target.toString().toLowerCase()
        val typesafePipelineSerializersOnClasspath: ServiceLoader<TypesafePipelineSerializer> =
            ServiceLoader.load(TypesafePipelineSerializer::class.java)

        return typesafePipelineSerializersOnClasspath.firstOrNull { pipelineSerializer: TypesafePipelineSerializer ->
            pipelineSerializer.javaClass.simpleName.startsWith(
                targetCiPipeline, true
            )
        }
    }

}