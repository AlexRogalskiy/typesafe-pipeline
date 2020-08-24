package com.github.pozo.typesafepipeline.dsl

import com.github.pozo.typesafepipeline.domain.GlobalPipelineContext


@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class PipelineDSL

@PipelineDSL
open class PipelineBuilder {

    private var agent: Agent? = null

    private var stages: Stages =
        Stages(listOf())

    @Deprecated(level = DeprecationLevel.ERROR, message = "Pipeline can't be nested.")
    internal fun pipeline(param: () -> Unit = {}) {
    }

    fun agent(init: (@PipelineDSL AgentBuilder).() -> Unit) {
        val agentBuilder = AgentBuilder()
        agentBuilder.init()
        agent = agentBuilder.build()
    }

    fun stages(init: (@PipelineDSL StagesBuilder).() -> Unit) {
        val stagesBuilder = StagesBuilder()
        stagesBuilder.init()
        stages = stagesBuilder.build()
    }

    internal fun build(): Pipeline {
        return Pipeline(agent, stages)
    }
}

@PipelineDSL
class StagesBuilder {

    private val stages = mutableListOf<Stage>()

    operator fun Stage.unaryPlus() {
        stages += this
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Stages can't be nested.")
    internal fun stages(param: () -> Unit = {}) {
    }

    fun stage(name: String, init: StageBuilder.() -> Unit) {
        val stageBuilder = StageBuilder(name)
        stageBuilder.init()
        stages += stageBuilder.build()
    }

    internal fun build(): Stages {
        return Stages(stages)
    }
}

@PipelineDSL
class StageBuilder(private val name: String) {

    private val steps = mutableListOf<Step>()

    @Deprecated(level = DeprecationLevel.ERROR, message = "Stage can't be nested.")
    internal fun stage(param: () -> Unit = {}) {
    }

    internal fun build(): Stage {
        return Stage(name, steps)
    }

    fun sh(command: String) {
        steps.add(Sh(command))
    }
}

@PipelineDSL
class AgentBuilder {

    private var image: String? = null

    @Deprecated(level = DeprecationLevel.ERROR, message = "Stage can't be nested.")
    internal fun agent(param: () -> Unit = {}) {
    }

    internal fun build(): Agent {
        return Agent(image)
    }

    fun docker(init: ImageBuilder.() -> Unit) {
        val imageBuilder = ImageBuilder()
        imageBuilder.init()
        image = imageBuilder.build()
    }
}

@PipelineDSL
class ImageBuilder {

    var image: String? = null

    @Deprecated(level = DeprecationLevel.ERROR, message = "Stage can't be nested.")
    fun image(param: () -> Unit = {}) {
    }

    internal fun build(): String? {
        return image
    }

}


fun pipeline(init: (@PipelineDSL PipelineBuilder).() -> Unit): GlobalPipelineContext {
    val pipelineBuilder = PipelineBuilder()
    pipelineBuilder.init()

    GlobalPipelineContext.pipeline = pipelineBuilder.build()
    if (pipelineSetup != null) {
        GlobalPipelineContext.pipelineSetup = pipelineSetup!!
    }


    return GlobalPipelineContext
}
