package com.github.pozo.typesafepipeline.dsl

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class CiDSL

@CiDSL
data class Setup(val target: Ci = Ci.None)


@CiDSL
open class PipelineConfigBuilder {

    var target: Ci =
        Ci.None

    var runMode: Mode =
        Mode.None

    @Deprecated(level = DeprecationLevel.ERROR, message = "Pipeline can't be nested.")
    fun target(param: () -> Unit = {}) {
    }

    fun mode(mode: Mode) {
        this.runMode = mode
    }

    fun target(target: Ci) {
        this.target = target
    }

    internal fun build(): Setup {
        return Setup(target)
    }
}

var pipelineSetup: Setup? = null

fun pipelineConfig(init: (@CiDSL PipelineConfigBuilder).() -> Unit): Setup? {
    val pipelineConfigBuilder = PipelineConfigBuilder()
    pipelineConfigBuilder.init()
    pipelineSetup = pipelineConfigBuilder.build()
    return pipelineSetup
}