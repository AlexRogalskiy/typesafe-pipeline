package com.github.pozo.typesafepipeline.dsl

enum class Ci {
    None,
    Gitlab,
    Jenkins,
}

enum class Mode {
    None,
    Transpile,
    Standalone,
}
