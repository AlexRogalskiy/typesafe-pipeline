import com.github.pozo.typesafepipeline.dsl.Ci
import com.github.pozo.typesafepipeline.dsl.Mode
import com.github.pozo.typesafepipeline.dsl.pipeline
import com.github.pozo.typesafepipeline.dsl.pipelineConfig

pipelineConfig {
    runMode = Mode.Transpile
    target = Ci.Gitlab
}

pipeline {
    agent {
        docker {
            image = "maven:3-alpine"
        }
    }

    stages {

        stage("stage one") {
            sh("mvn --version")
        }

        stage("stage two") {

        }
    }
}


