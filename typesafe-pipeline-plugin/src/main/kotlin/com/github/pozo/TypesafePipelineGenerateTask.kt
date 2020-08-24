package com.github.pozo

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.net.URL
import java.net.URLClassLoader


open class TypesafePipelineGenerateTask : DefaultTask() {

    @TaskAction
    fun generate() {
        val extension = project.extensions.getByName("typesafePipeline") as TypesafePipelineExtension
        val descriptorFile = extension.descriptorFile
        logger.debug("descriptorFile: $descriptorFile")

        val script = project.file(descriptorFile).readText(Charsets.UTF_8)

        val sysClassLoader = ClassLoader.getSystemClassLoader()

        val urls: Array<URL> = (sysClassLoader as URLClassLoader).urLs

        logger.debug("classpath:")
        for (i in urls.indices) {
            logger.debug("xx: " + (urls[i].getFile()))
        }

        logger.debug("generate: $script")
        val typesafePipelineParser = TypesafePipelineParser()
        val pipelineContext = typesafePipelineParser.parse(script)

        logger.debug("build was successful: $pipelineContext")
    }
}