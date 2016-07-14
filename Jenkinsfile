node(){
    stage 'Checkout Source Code'
    checkout scm

    parallel 'Build Maven Project':{
        node(){
            def mvnHome = tool 'MAVEN_TOOL'
            sh "echo ${mvnHome}"
            sh "${mvnHome}/bin/mvn clean install"
        }
    }, 'Build Other Projects':{
        node(){}
    }

}