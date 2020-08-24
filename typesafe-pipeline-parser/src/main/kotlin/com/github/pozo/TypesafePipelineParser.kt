package com.github.pozo

import com.github.pozo.typesafepipeline.domain.GlobalPipelineContext
import javax.script.ScriptEngineManager

class TypesafePipelineParser {

    fun parse(script: String): GlobalPipelineContext {
        val engine = ScriptEngineManager().getEngineByExtension("kts")
        return engine.eval(script) as GlobalPipelineContext
    }
}
