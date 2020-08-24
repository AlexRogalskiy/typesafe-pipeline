import com.github.pozo.Ci.Jenkins
import com.github.pozo.Mode.Transpile
import com.github.pozo.pipeline
import com.github.pozo.pipelineConfig

pipelineConfig {
    runMode = Transpile
    target = Jenkins
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


