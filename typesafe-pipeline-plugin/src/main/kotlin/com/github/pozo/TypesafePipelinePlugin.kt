package com.github.pozo

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class TypesafePipelinePlugin : Plugin<Project> {

    private val logger: Logger = Logging.getLogger(TypesafePipelinePlugin::class.java)

    override fun apply(project: Project) {
        val extension = project.extensions.run {
            create("typesafePipeline", TypesafePipelineExtension::class.java)
        }
        logger.debug("descriptorFile: $extension")

        project.tasks.register("generate", TypesafePipelineGenerateTask::class.java)
    }
}
