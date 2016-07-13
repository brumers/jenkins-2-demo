node(){
    stage 'Checkout Source Code'
    checkout scm

    stage 'Build Maven Project'
    def mvnHome = tool 'MAVEN_TOOL'
    sh "echo ${mvnHome}"
    sh "${mvnHome}/bin/mvn clean install"

}