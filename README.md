## Typesafe Pipeline

The intention of this repository is creating a tool which would provide a possibility to write portable, testable and typesafe CI/CD pipelines. The status of this description and the repository is still work in progress. Any ideas are welcome.  

The supported vendors would be the ones which are providing possibility to use pipeline as a code and containers, for example Jenkins and Gitlab.   

#### High level description 

An example usage would be the something like this:

 - Creating `Pipeline.kts` file, and use Kotlin DSL for writing the pipeline
 - Write some test against the pipeline e.g.:
   - A specific event triggered a stage  
   - A written command in a step is available in the specified container
   - At some point a slack notification triggered 
 - Using the maven/gradle plugin in order to run the tests against the pipeline
 - Using the maven/gradle plugin for generating the production pipeline to a specific vendor, for example gitlab's `.gitlab-ci.yml` file
 
#### Project structure

 - `typesafe-pipeline-core` contains the domain related logic and DSL
 - `typesafe-pipeline-parser` responsible for evaluate kts files 
 - `typesafe-pipeline-gitlab` responsible for generating gitlab's `.gitlab-ci.yml` files
 - `typesafe-pipeline-plugin` contains the gradle plugin related code
 - `example` responsible for demonstrating this library usage
 - `debug` responsible for demonstrating this library usage

# Licensing 

Please see LICENSE file

# Contact

Zoltan Polgar - pozo@gmx.com

Please do not hesitate to contact me if you have any further questions.
