package com.github.pozo.typesafepipeline.dsl

data class Agent(val image: String?)

open class Step(val name: String)

data class Sh(val command: String) : Step("sh")

data class Stage(val name: String, val steps: List<Step>)

data class Stages(val stages: List<Stage> = emptyList())

data class Pipeline(val agent: Agent? = null, val stages: Stages = Stages())
